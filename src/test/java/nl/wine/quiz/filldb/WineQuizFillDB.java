package nl.wine.quiz.filldb;

import nl.wine.quiz.model.Wine;
import nl.wine.quiz.model.enums.WineRegion;
import nl.wine.quiz.service.hibernate.HibernateService;
import nl.wine.quiz.service.hibernate.HibernateServiceImpl;

public class WineQuizFillDB
{
    private HibernateService hibernateService = new HibernateServiceImpl();

    public static void main(String[] args)
    {
        new WineQuizFillDB().createAndFillDb();
    }

    private void createAndFillDb()
    {
        saveWines();
    }

    private void saveWines()
    {

        Wine wine1 = createWine("Bourgueil", WineRegion.VALLEE_DE_LA_LOIRE);
        Wine wine2 = createWine("Pouilly-fuissé", WineRegion.BOURGOGNE);
        Wine wine3 = createWine("Sauternes", WineRegion.BORDEAUX);
        Wine wine4 = createWine("Saint-amour", WineRegion.BEAUJOLAIS);

        hibernateService.saveOrUpdate(wine1);
        hibernateService.saveOrUpdate(wine2);
        hibernateService.saveOrUpdate(wine3);
        hibernateService.saveOrUpdate(wine4);
    }

    private Wine createWine(String name, WineRegion region)
    {
        Wine wine = new Wine();
        wine.setWineName(name);
        wine.setWineRegion(region);
        return wine;
    }


}
