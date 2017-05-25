package kr.co.sist.movie.vo;

public class ResInfoVO {
   //movie_title,am.movie_info,am.movie_opendate,ar.user_id,ar.res_code,ar.seat_quan,ans.seat_num,ar.movie_code
   private String movieTitle,movieInfo,openDate,id,movie_code,resCode;
   private int  seatNum,seatQuan,resCnt;
   
   
   public String getMovieTitle() {
      return movieTitle;
   }
   public void setMovieTitle(String movieTitle) {
      this.movieTitle = movieTitle;
   }
   public String getMovieInfo() {
      return movieInfo;
   }
   public void setMovieInfo(String movieInfo) {
      this.movieInfo = movieInfo;
   }
   public String getOpenDate() {
      return openDate;
   }
   public void setOpenDate(String openDate) {
      this.openDate = openDate;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getMovie_code() {
      return movie_code;
   }
   public void setMovie_code(String movie_code) {
      this.movie_code = movie_code;
   }
   public String getResCode() {
      return resCode;
   }
   public void setResCode(String resCode) {
      this.resCode = resCode;
   }
   public Integer getSeatNum() {
      return seatNum;
   }
   public void setSeatNum(Integer seatNum) {
      this.seatNum = seatNum;
   }
   public Integer getSeatQuan() {
      return seatQuan;
   }
   public void setSeatQuan(Integer seatQuan) {
      this.seatQuan = seatQuan;
   }
   public Integer getResCnt() {
      return resCnt;
   }
   public void setResCnt(Integer resCnt) {
      this.resCnt = resCnt;
   }
   @Override
   public String toString() {
      return "ResInfoVO [movieTitle=" + movieTitle + ", movieInfo=" + movieInfo + ", openDate=" + openDate + ", id="
            + id + ", movie_code=" + movie_code + ", resCode=" + resCode + ", seatNum=" + seatNum + ", seatQuan="
            + seatQuan + ", resCnt=" + resCnt + "]";
   }

   
   
}