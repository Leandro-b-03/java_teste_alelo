
public class Movie {
	private String name;
	private String releaseDate;
	

	public String getName() { return this.name; }
	private void setName(String name) { this.name = name; }

	public String getreleaseDate() { return this.releaseDate; }
	private void setreleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
	
	Movie(String name, String releaseDate) {
		this.setName(name);
		this.setreleaseDate(releaseDate);
	}
}
