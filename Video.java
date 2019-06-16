public class Video  implements Comparable<Video>{
	private String url;
	private String name;
	private String publisher;
	private String year;
	private String views;
	
	/*** Default constructor ***/
	public Video() {
		this("Unknown URL", "Unknown Title", "Unknown publisher", "Unknown Year", "Unknown Views");
	}
	
	/*** 5 parameters constructor ***/
	public Video (String url, String name, String publisher, String year, String views) {
		this.url = url;
		this.name = name;
		this.publisher =publisher;
		this.year = year;
		this.views = views;
	}
	
	/*** 3 Parameter Constructor ***/
	public Video (String url, String name) {
		this(url, name, "unknown publisher", "unknown year", "unknown views");
	}
	
	/*** Setters ***/
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	public void setViews(String views) {
		this.views = views;
	}
	
	/*** Getters ***/
	public String getUrl() {
		return url;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public String getYear() {
		return year;
	}
	public String getViews() {
		return views;
	}

	
	/*** Creates a String of YouTubeDataBase information ***/
    @Override public String toString() {
        return "URL: " + url + "\n" + 
        		"Video Title: " + name + "\n" + 
        		"Publisher: " + publisher + "\n" + 
        		"Year: " + year + "\n" +
        		"Views: " + views + "\n"; 
    }

    @Override public boolean equals(Object o) {
    	if(this == o) {
    		return true;
    	} else if (!(o instanceof Video)) {
    		return false;
    	} else {
    		Video v = (Video) o;
    		return url.equals(v.url) && name.equals(v.name) && publisher.equals(publisher) && year.equals(year) && views.equals(views);
    	}
    }
    
	@Override
	public int compareTo(Video o) {
	   	if(this.equals(o)){
    		return 0;
    	} else if (!url.equals(o.url)) {
    		return url.compareTo(o.url);
    	} else if (!name.equals(o.name)) {
    		return name.compareTo(o.name);
    	} else if (!publisher.equals(o.publisher)) {
    		return publisher.compareTo(o.publisher);
    	} else if (!year.equals(o.year)) {
    		return year.compareTo(o.year);
    	} else {
    		return views.compareTo(o.views);
    	}
    }
	
	public int hashCodeByUrl() {
		String urlSubString = url.substring(url.indexOf("=")+1, url.length());
        int sum = 0;
        for (int i = 0; i < urlSubString.length(); i++) {
            sum += (int) urlSubString.charAt(i);
        }
        return sum;
    }
	
	public int hashCodeByVideoName() {
		int sum = 0;
		for(int i = 0; i< name.length(); i++) {
			sum += (int)name.charAt(i);
		}
		return sum;
	}
	
	public int compareToByUrl(Video o) {
		return url.compareTo(o.url);
	}
	
	public int compareToByVideoName(Video o) {
		return name.compareTo(o.name);
	}
}
