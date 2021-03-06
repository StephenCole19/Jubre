 /* @author justend29
 */
public class CourseOfferingInfoObject {
    public int id;
  public int courseId;
  public String roomNumber;
  public double courseLength; // in minutes
  public int term; // 1 - 4
  public int year;
  public int professorId;
  public int TA_id;
  public boolean[] daysOfWeek = new boolean[]{false,false,false,false,false};
  public String time;
    public String courseNum;

    public CourseOfferingInfoObject(int id, int courseId, String roomNum,
                                  double courseLength, int term, int year,
                                  int profId, int TA_id,
                                  boolean[] dow, String time) {
        this.id = id;
    this.courseId = courseId;
    this.roomNumber = roomNum;
    this.courseLength = courseLength;
    this.term = term;
    this.year = year;
    this.professorId = profId;
    this.TA_id = TA_id;
    this.daysOfWeek = dow;
    this.time = time;
    this.courseNum = null;
  }
  
  public CourseOfferingInfoObject(int courseId, String courseNum, int term, int year) {
 	  this.courseId = courseId;
	  this.roomNumber = null;
	  this.courseLength = 0;
	  this.term = term;
	  this.year = year;
	  this.professorId = 0;
	  this.TA_id = 0;
	  this.daysOfWeek = null;
	  this.time = null;
	  this.courseNum = courseNum;
  }
  
  
  public String toString() {
	  String termName = "N/A";
	  if(term == 0) {
		  termName = "Fall";
	  } else if (term == 1) {
		  termName = "Winter";
	  } else if (term == 2) {
		  termName = "SummerA";
	  } else if (term == 3) {
		  termName = "SummerB";
	  }
	  return courseNum + " Term: " + termName + " " + year + " ID:" + courseId;
  }

  public int getCourseId() {
    return courseId;
  }
  public String getRoomNumber() {
    return roomNumber;
  }
  public double getCourseLength() {
    return courseLength;
  }
  public int getTerm() {
    return term;
  }
  public int getYear() {
    return year;
  }
  public int getProfessorId() {
    return professorId;
  }
  public int getTaId() {
    return TA_id;
  }
  public boolean[] getDOW() {
    return daysOfWeek;
  }
  public String getTime() {
    return time; 
  }
  public String getCourseNum() {
	  return courseNum;
  }
  public int getOfferingId() {
	  return id;
  }

}
