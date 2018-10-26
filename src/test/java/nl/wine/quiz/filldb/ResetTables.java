package nl.wine.quiz.filldb;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ResetTables
{
    public static void resetTables(Session session)
    {
        List<String> dropStatements = readResetSql(session);
        executeDropStatements(session, dropStatements);

    }

    private static void executeDropStatements(Session session, List<String> dropStatements)
    {
        List<String> notDroppedStatements = new ArrayList<>();
        session = session.getSessionFactory().openSession();

        drop(session, dropStatements, notDroppedStatements);

        dropStatements = notDroppedStatements;
        notDroppedStatements = new ArrayList<>();

        drop(session, dropStatements, notDroppedStatements);

        if (notDroppedStatements.size() > 0)
        {
            throw new IllegalStateException("Not succesfully deleted all records: " + notDroppedStatements.toString());
        }

        session.close();
    }

    private static void drop(Session session, List<String> dropStatements, List<String> notDroppedStatements)
    {
        Transaction transaction = null;
        for (String dropStatement : dropStatements)
        {
            try
            {
                transaction = session.beginTransaction();
                Query query = session.createSQLQuery(dropStatement);
                query.executeUpdate();
                transaction.commit();
            }
            catch (Exception e)
            {
                notDroppedStatements.add(dropStatement);
                System.out.println("Error while dropping tables " + e);
                transaction.rollback();
            }
        }
    }

    private static List<String> readResetSql(Session session)
    {
        ArrayList<String> dropStatements = new ArrayList<>();

        try
        {
            Transaction transaction = session.beginTransaction();
            String allTables = "SELECT concat(schemaname, '.', relname) FROM pg_stat_user_tables";
            Query query = session.createSQLQuery(allTables);
            List<String> var = query.list();

            var.forEach(tableName -> dropStatements.add("DELETE FROM " + tableName));

            dropStatements.add("ALTER SEQUENCE hibernate_sequence RESTART WITH 1");
            transaction.commit();
            return dropStatements;
        }
        catch (Exception e)
        {
            System.out.print("Error while creating executeDropStatements statements for tables " + e);
        }
        return dropStatements;
    }
}
