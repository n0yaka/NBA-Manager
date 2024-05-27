/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;

/**
 *
 * @author radin
 */
public class NBAPlayer {
    
    private int id;
    private String name;
    private String team;
    private int salary;
    private double points;
    private double rebounds;
    private double assists;
    private double steals;
    private double blocks;

    public NBAPlayer(int id, String name, String team, int salary, double points, double rebounds, double assists, double steals, double blocks) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.salary = salary;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public String getTeam() {return team;}

    public int getSalary() {return salary;}

    public double getPoints() {return points;}

    public double getRebounds() {return rebounds;}

    public double getAssists() {return assists;}

    public double getSteals() {return steals;}

    public double getBlocks() {return blocks;}

    @Override
    public String toString() {
        return "NBAPlayer{" + "id=" + id + ", name=" + name + ", team=" + team + ", salary=" + salary + ", points=" + points + ", rebounds=" + rebounds + ", assists=" + assists + ", steals=" + steals + ", blocks=" + blocks + '}';
    }
    
    
    
}
