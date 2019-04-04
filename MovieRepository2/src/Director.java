import java.util.ArrayList;

/**
 * @author chron
 *
 */
public class Director {
	private String name;
	private String birth;
	
	public ArrayList<Movie> getMovies() { return Movies; }
	private ArrayList<Movie> Movies = new ArrayList<Movie>();
	

	public String getName() { return this.name; }
	private void setName(String name) { this.name = name; }

	public String getBirth() { return this.birth; }
	private void setBirth(String birth) { this.birth = birth; }
	
	Director(String name, String birth) {
		this.setName(name);
		this.setBirth(birth);
	}
	
	public Movie insertMovie (String name, String release_date ) {
		Movie movie = new Movie(name, release_date);
		
		Movies.add(movie);
		
		return movie;
	}
}
