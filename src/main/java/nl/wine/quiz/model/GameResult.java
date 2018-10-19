package nl.wine.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class GameResult
{
    @Id
    @GeneratedValue
    private int gameResultId;

    @OneToOne
    private Player player;

    @OneToOne
    private Wine wine;

    @Column(nullable = false)
    private int numberOfTimesWrong;

}
