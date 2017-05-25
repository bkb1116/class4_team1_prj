package kr.co.sist.movie.vo;

public class MovieRegisVO {
   private String movieName, movieInfo, movieImg, movieDate;

   public String getMovieName() {
      return movieName;
   }

   public void setMovieName(String movieName) {
      this.movieName = movieName;
   }

   public String getMovieInfo() {
      return movieInfo;
   }

   public void setMovieInfo(String movieInfo) {
      this.movieInfo = movieInfo;
   }

   public String getMovieImg() {
      return movieImg;
   }

   public void setMovieImg(String movieImg) {
      this.movieImg = movieImg;
   }

   public String getMovieDate() {
      return movieDate;
   }

   public void setMovieDate(String movieDate) {
      this.movieDate = movieDate;
   }

   @Override
   public String toString() {
      return "MovieRegisVO [movieName=" + movieName + ", movieInfo=" + movieInfo + ", movieImg=" + movieImg
            + ", movieDate=" + movieDate + "]";
   }
   
}//class