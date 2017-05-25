package kr.co.sist.movie.vo;

public class AddReviewVO {
	private String movieReview, id;
	private int movieScore;
	
	public int getMovieScore() {
		return movieScore;
	}

	public void setMovieScore(int movieScore) {
		this.movieScore = movieScore;
	}

	public String getMovieReview() {
		return movieReview;
	}

	public void setMovieReview(String movieReview) {
		this.movieReview = movieReview;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
