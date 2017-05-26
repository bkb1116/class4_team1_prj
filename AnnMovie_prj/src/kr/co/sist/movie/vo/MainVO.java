package kr.co.sist.movie.vo;

public class MainVO {
	private String movieImg, movieCode, movieTitle; 
	private int avgScore, peopleNum;
	
	public String getMovieImg() {
		return movieImg;
	}

	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}

	public String getMovieCode() {
		return movieCode;
	}

	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public int getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(int avgScore) {
		this.avgScore = avgScore;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

	@Override
	public String toString() {
		return "MainVO [movieImg=" + movieImg + ", movieCode=" + movieCode + ", movieTitle=" + movieTitle
				+ ", avgScore=" + avgScore + ", peopleNum=" + peopleNum + "]";
	}

}
