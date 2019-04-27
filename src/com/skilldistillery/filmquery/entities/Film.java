package com.skilldistillery.filmquery.entities;

public class Film {

	private int id;
	private String title;
	private String description;
	private String releaseYear;
	private String langId;
	private String rentDur;
	private String rentRate;
	private int length;
	private double replaceCost;
	private String rating;
	private String specialFeat;
	
	public Film() {
		super();
	}

	
	public Film(int id, String title, String description, String releaseYear, String langId, String rentDur, String rentRate,
			int length, double replaceCost, String rating, String specialFeat) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rentRate = rentRate;
		this.length = length;
		this.replaceCost = replaceCost;
		this.rating = rating;
		this.specialFeat = specialFeat;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getRentDur() {
		return rentDur;
	}

	public void setRentDur(String rentDur) {
		this.rentDur = rentDur;
	}

	public String getRentRate() {
		return rentRate;
	}

	public void setRentRate(String rentRate) {
		this.rentRate = rentRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getReplaceCost() {
		return replaceCost;
	}

	public void setReplaceCost(double replaceCost) {
		this.replaceCost = replaceCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeat() {
		return specialFeat;
	}

	public void setSpecialFeat(String specialFeat) {
		this.specialFeat = specialFeat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((langId == null) ? 0 : langId.hashCode());
		result = prime * result + length;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((rentDur == null) ? 0 : rentDur.hashCode());
		result = prime * result + ((rentRate == null) ? 0 : rentRate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(replaceCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((specialFeat == null) ? 0 : specialFeat.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((releaseYear == null) ? 0 : releaseYear.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (langId == null) {
			if (other.langId != null)
				return false;
		} else if (!langId.equals(other.langId))
			return false;
		if (length != other.length)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (rentDur == null) {
			if (other.rentDur != null)
				return false;
		} else if (!rentDur.equals(other.rentDur))
			return false;
		if (rentRate == null) {
			if (other.rentRate != null)
				return false;
		} else if (!rentRate.equals(other.rentRate))
			return false;
		if (Double.doubleToLongBits(replaceCost) != Double.doubleToLongBits(other.replaceCost))
			return false;
		if (specialFeat == null) {
			if (other.specialFeat != null)
				return false;
		} else if (!specialFeat.equals(other.specialFeat))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (releaseYear == null) {
			if (other.releaseYear != null)
				return false;
		} else if (!releaseYear.equals(other.releaseYear))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", year=" + releaseYear + ", landId="
				+ langId + ", rentDur=" + rentDur + ", rentRate=" + rentRate + ", length=" + length + ", replaceCost="
				+ replaceCost + ", rating=" + rating + ", specialFeat=" + specialFeat + "]";
	}

}
