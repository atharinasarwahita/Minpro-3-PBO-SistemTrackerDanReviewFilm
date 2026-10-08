package models;

public class Review implements Displayable{
    private final int idReview;
    private final int idFilm;
    private double rating;
    private String ulasan;
    private final boolean isRewatch;
    
    //Constructor
    public Review(int idReview, int idFilm, double rating, String ulasan, boolean isRewatch) {
        this.idReview = idReview;
        this.idFilm = idFilm;
        setRating(rating);
        this.ulasan = ulasan;
        this.isRewatch = isRewatch;
    }
        
    public int getIdReview() {
        return idReview; 
    }
    
    public int getIdFilm() {
        return idFilm;
    }
    
    public String getUlasan() {
        return ulasan; 
    }
    
    public void setUlasan(String ulasan) {
        if (ulasan != null && !ulasan.isEmpty()) {
            this.ulasan = ulasan;
        }
    }
    
    public double getRating() {
        return rating; 
    }
    
    public boolean setRating(double rating) {
    if (rating >= 1.0 && rating <= 5.0) {
        this.rating = rating;
        return true;
    } else {
        System.out.println("Rating harus antara 1.0 sampai 5.0!");
        return false;
      }
    }
    
    public boolean isRewatch(){
        return isRewatch;
    }
    
    // implementasi dari interface Displayable
    @Override
    public void tampilkanInfo(boolean ringkas) {
            if (ringkas) {
                String tag = isRewatch ? "(Rewatch)" : "";
                System.out.println("     Review" + idReview + tag + " | " + getRating());
            } else {
                    if (isRewatch) {
                        System.out.println("     -------------------------------------------------------------");
                        System.out.println("     [REWATCH] [ID Review: " + idReview + "]");
                    } else {
                        System.out.println("     [ID Review: " + idReview + "]");
                    }
                    System.out.println("     Rating : " + getRating());
                    System.out.println("     Ulasan : " + ulasan);
                    }
            }
        }
