/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nba.ds.asgmnt;

/**
 *
 * @author radin
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author radin
 */
public class NBAPlayer {
    
    private int id;
    private String name;
    private String team;
    private String position;
    private double points;
    private double rebounds;
    private double assists;
    private double steals;
    private double blocks;
    private double salary;

    public NBAPlayer(int id, String name, String team, String position, double points, double rebounds, double assists, double blocks, double steals, double salary, double steals1, double salary1) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.position = position;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.salary = salary;
    }

    // Getters for attributes
    public int getId() { return id; }
    public String getName() { return name; }
    public String getTeam() { return team; }
    public String getPosition() { return position; }
    public double getPoints() { return points; }
    public double getRebounds() { return rebounds; }
    public double getAssists() { return assists; }
    public double getSteals() { return steals; }
    public double getBlocks() { return blocks; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return "NBAPlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", position='" + position + '\'' +
                ", points=" + points +
                ", rebounds=" + rebounds +
                ", assists=" + assists +
                ", steals=" + steals +
                ", blocks=" + blocks +
                ", salary=" + salary +
                '}';
    }
}