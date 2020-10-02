package be.pxl.ja.streamingservice.model;


import be.pxl.ja.streamingservice.exception.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Queue;

public class Profile {
	private static final int MAX_RECENTLY_WATCHED_SIZE = 5;
	private static final int MAX_CURRENTLY_WATCHING_SIZE = 3;
	private String name;
	private LocalDate dateOfBirth;
	private Queue<Content> recentlyWatched = new LinkedList<>();
	private Queue<Content> currentlyWatching = new LinkedList<>();

	public Profile(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		if (dateOfBirth.isAfter(LocalDate.now())) {
			throw new InvalidDateException(dateOfBirth, "date of birth", "No date of birth in future allowed.");
		}
		this.dateOfBirth = dateOfBirth;
	}

	public int getAge() {
		if (dateOfBirth == null) {
			return 0;
		}
		return (int) ChronoUnit.YEARS.between(dateOfBirth, LocalDateTime.now());
	}

	public void startWatching(Content content) {
		currentlyWatching.offer(content);
		while (currentlyWatching.size() > MAX_CURRENTLY_WATCHING_SIZE) {
			currentlyWatching.remove();
		}
	}

	public void finishWatching(Content content) {
		currentlyWatching.remove(content);
		recentlyWatched.add(content);
		while (recentlyWatched.size() == MAX_RECENTLY_WATCHED_SIZE) {
			recentlyWatched.remove();
		}
	}

	public boolean allowedToWatch(Content content) {
		return content.getMaturityRating().getMinimumAge() <= getAge();
	}
}
