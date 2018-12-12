import java.lang.*;

public class Planet{
	public static final double GRAV_CONST = 6.67E-11;

	public double xxPos,
		   yyPos,
		   xxVel,
		   yyVel,
		   mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }

    public Planet(Planet p){
    	xxPos = p.xxPos;
    	yyPos = p.yyPos;
    	xxVel = p.xxVel;
    	yyVel = p.yyVel;
    	mass = p.mass;
    	imgFileName = p.imgFileName;
    }

    double calcDistance(Planet other){ // r^2=dx^2+dy^2
    	double dx = other.xxPos-xxPos;
    	double dy = other.yyPos-yyPos;
    	return Math.sqrt(dx*dx+dy*dy);
    }

    double calcForceExertedBy(Planet other){ // F=(G*m1*m2)/r^2
    	double distance = calcDistance(other);
    	return GRAV_CONST*mass*other.mass/(distance*distance);
    }

    double calcForceExertedByX(Planet other){ // (F*dx)/r
    	return calcForceExertedBy(other)*(other.xxPos-xxPos)/calcDistance(other);
    }

    double calcForceExertedByY(Planet other){
    	return calcForceExertedBy(other)*(other.yyPos-yyPos)/calcDistance(other);
    }

    double calcNetForceExertedByX(Planet[] planets){
    	double netForceX = 0;
    	for(Planet p : planets)
    		if(!(this.equals(p)))
    			netForceX += calcForceExertedByX(p);
    	return netForceX;
    }

    double calcNetForceExertedByY(Planet[] planets){
    	double netForceY = 0;
    	for(Planet p : planets)
    		if(!(this.equals(p)))
    			netForceY += calcForceExertedByY(p);
    	return netForceY;
    }

    public void update(double dt, double fX, double fY){
    	double aNetX = fX / mass;
    	double aNetY = fY / mass;
    	xxVel = xxVel + dt * aNetX;
    	yyVel = yyVel + dt * aNetY;
    	xxPos = xxPos + dt * xxVel;
    	yyPos = yyPos + dt * yyVel;
    }
}