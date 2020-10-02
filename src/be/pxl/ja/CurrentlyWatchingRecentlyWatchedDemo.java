package be.pxl.ja;

import be.pxl.ja.streamingservice.model.Movie;
import be.pxl.ja.streamingservice.model.Profile;
import be.pxl.ja.streamingservice.model.Rating;

public class CurrentlyWatchingRecentlyWatchedDemo {

	public static void main(String[] args) {
		Profile emily = new Profile("Emily");

		emily.startWatching(new Movie("Brother Bear", Rating.LITTLE_KIDS));
		emily.startWatching(new Movie("Dance Academy", Rating.LITTLE_KIDS));
		emily.startWatching(new Movie("Brother Bear", Rating.LITTLE_KIDS));
	}

}
