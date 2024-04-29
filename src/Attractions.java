
public class Attractions {

	private String attractionname;
	private String description;
	private String city;
	private String tag;
	//private float rating;
	private boolean status;
	
	

	public Attractions(String n, String d, String c, String t, boolean s) 
	{
		 this.attractionname= n;
		 this.description = d;
		 this.city = c;
		 this.tag = t;
		 //this.rating = r;
		 this.status = s;
		 
	}


	public String getattractionname() {
		return attractionname;
	}



	public void setattractionname(String attractionname) {
		this.attractionname = attractionname;
	}



	public String getdescription() {
		return description;
	}



	public void setdescription(String description) {
		this.description = description;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



//	public float getRating() {
//		return rating;
//	}
//
//
//
//	public void setRating(float rating) {
//		this.rating = rating;
//	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}

}
