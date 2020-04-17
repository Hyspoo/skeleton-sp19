public class Body {

	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;
	public static double G = 6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Body(Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}
	
	public double calcDistance(Body b) {
		double dx = Math.abs(this.xxPos - b.xxPos);
		double dy = Math.abs(this.yyPos - b.yyPos);
		return Math.sqrt(dx*dx + dy*dy);
	}

	public double calcForceExertedBy(Body b) {
		double dis = this.calcDistance(b);
		return (G*this.mass*b.mass)/(dis*dis);
	}

	public double calcForceExertedByX(Body b) {
		double dis = this.calcDistance(b);
		double force = this.calcForceExertedBy(b);
		return force*(b.xxPos - this.xxPos)/dis;
	}

	public double calcForceExertedByY(Body b) {
		double dis = this.calcDistance(b);
		double force = this.calcForceExertedBy(b);
		return force*(b.yyPos - this.yyPos)/dis;
	}

	public double calcNetForceExertedByX(Body[] allBodys) {
		double fX = 0;
		for (Body i: allBodys) {
			if (this != i) {
				fX += this.calcForceExertedByX(i);
			}
		}
		return fX;
	}

	public double calcNetForceExertedByY(Body[] allBodys) {
		double fY = 0;
		for (Body i: allBodys) {
			if (this != i) {
				fY += this.calcForceExertedByY(i);
			}
		}
		return fY;
	}

	public void update(double dt, double fX, double fY) {
		this.xxVel += dt*fX/this.mass;
		this.yyVel += dt*fY/this.mass;
		this.xxPos += dt*this.xxVel;
		this.yyPos += dt*this.yyVel;
	}
}
