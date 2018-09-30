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
        drop(session, dropStatements);

    }

    private static void drop(Session session, List<String> dropStatements)
    {
        session = session.getSessionFactory().openSession();
        for (String dropStatement : dropStatements)
        {
            try
            {
                Transaction transaction = session.beginTransaction();

                Query query = session.createSQLQuery(dropStatement);
                query.executeUpdate();

                transaction.commit();

            }
            catch (Exception e)
            {
                System.out.println("Error while dropping tables " + e);
            }
        }
        session.close();
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
            System.out.print("Error while creating drop statements for tables " + e);
        }
        return dropStatements;
    }
}
