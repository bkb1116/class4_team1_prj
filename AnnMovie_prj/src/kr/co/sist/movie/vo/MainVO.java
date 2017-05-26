package kr.co.sist.movie.vo;

public class MainVO {
	private String movieImg, movieInfo, movieTitle; 
	private int avgScore;
	
	public String getMovieImg() {
		return movieImg;
	}

	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}

	public String getMovieInfo() {
		return movieInfo;
	}

	public void setMovieInfo(String movieInfo) {
		this.movieInfo = movieInfo;
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

	@Override
	public String toString() {
		return "MainVO [movieImg=" + movieImg + ", movieInfo=" + movieInfo + ", avgScore=" + avgScore + "]";
	}
}
