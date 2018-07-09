package popscore.pop;

public class Scores {
	public double ageScore, genScore, monScore;
	public double ageVisit, genVisit, monVisit;

	public Scores(double as, double av, double gs, double gv, double ms, double mv) {
		this.ageScore = as;
		this.ageVisit = av;
		this.genScore = gs;
		this.genVisit = gv;
		this.monScore = ms;
		this.monVisit = mv;
	}

	public void addAge(double score, double visit) {
		ageScore += score;
		ageVisit += visit;
	}

	public void addGen(double score, double visit) {
		genScore += score;
		genVisit += visit;
	}

	public void addMon(double score, double visit) {
		monScore += score;
		monVisit += visit;
	}

	public void printAgeInfo() {
		System.out.print(ageScore + ", " + ageVisit);
	}

	public void printGenInfo() {
		System.out.print(genScore + ", " + genVisit);
	}

	public void printMonInfo() {
		System.out.print(monScore + ", " + monVisit);
	}

	public double calcPS(double a, double b, double c) {
		double AgeS = 0, GenS = 0, MonS = 0;
		if (ageVisit > 0)
			AgeS = ageScore / ageVisit;
		if (genVisit > 0)
			GenS = genScore / genVisit;
		if (monVisit > 0)
			MonS = monScore / monVisit;
		return a * AgeS + b * GenS + c * MonS;
	}
}
