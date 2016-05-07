
package network;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class CJRVolley {
	
	private static RequestQueue mRequestQueue;
	private static RequestQueue mImageRequestQueue;
	
	
	private CJRVolley() {}
	
	/**
	 * initialize Volley
	 */
	public static void init(Context context) {
		mRequestQueue = Volley.newRequestQueue(context);
		mImageRequestQueue = Volley.newRequestQueue(context);
		//ImageCacheManager.INSTANCE.initImageCache();
	}
	
	public static RequestQueue getRequestQueue(Context context) {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			return mRequestQueue = Volley.newRequestQueue(context);
		}
	}
	
	public static RequestQueue getImageRequestQueue() {

		if (mImageRequestQueue != null) {
			return mImageRequestQueue;
		} else {
			throw new IllegalStateException("Image RequestQueue not initialized");
		}
	}
}
