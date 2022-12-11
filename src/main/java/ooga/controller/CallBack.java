package ooga.controller;

import org.json.simple.JSONObject;

/**
 *  Used to circumvent the asynchronous updating of the save file from the Firebase web database.
 *  I apologize because there may be a better way of "solving" asynchronous activity resulting
 *  in null variables but this was the only one I tried so far that was effective.
 */
public interface CallBack {
        /**
         * method allows user to retrieve the JSONObject (our file) needed from the firebase.
         * @param value the save file contents
         */
        void onCallback(JSONObject value);

}

