package home.mad.simpleshop.model.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import home.mad.simpleshop.model.dto.ItemDTO;
import rx.Observable;

/**
 * Created by mad on 05.12.2016.
 */

public class DatabaseImpl extends SQLiteOpenHelper implements Database {

    private final String TAG = getClass().getSimpleName();

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_FAVORITES_NAME = "favorites";
    public static final String KEY_ID = "_id";
    public static final String KEY_LISTING_ID = "listing_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_ITEM_DESCRIPTION = "description";
    public static final String KEY_PRICE = "price";
    public static final String KEY_PRICE_DESCRIPTION = "price_description";
    public static final String KEY_CATEGORY = "category";
    public static final String KEY_IMAGE_URL_MEDIUM = "image_medium";
    public static final String KEY_IMAGE_URL_LARGE = "image_large";
    public static final String KEY_IMAGE_URL_FULL = "image_full";


    public DatabaseImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public Observable<List<ItemDTO>> getFavorites(String category) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_FAVORITES_NAME + (category != null ? " WHERE " + KEY_CATEGORY + " = '" + category + "';" : ";"), null);
        ArrayList<ItemDTO> items = new ArrayList<>();
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            items.add(getItemFromCursor(cursor));
        }
        return Observable.just(items);
    }

    @Override
    public void addFavorite(ItemDTO item) {
        try {
//            getReadableDatabase().execSQL("INSERT INTO " + TABLE_FAVORITES_NAME + " VALUES (" + getDataFromItem(item) + ");");
            getWritableDatabase().execSQL(String.format("INSERT INTO %1s (%2s, %3s, %4s, %5s, %6s, %7s, %8s, %9s, %10s) VALUES (%11s);",
                    TABLE_FAVORITES_NAME,
                    KEY_LISTING_ID,
                    KEY_TITLE,
                    KEY_ITEM_DESCRIPTION,
                    KEY_PRICE,
                    KEY_PRICE_DESCRIPTION,
                    KEY_CATEGORY,
                    KEY_IMAGE_URL_MEDIUM,
                    KEY_IMAGE_URL_LARGE,
                    KEY_IMAGE_URL_FULL,
                    getDataFromItem(item)));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Observable<Boolean> isItemRemoved(long id) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_FAVORITES_NAME + " WHERE " + KEY_LISTING_ID + " = '" + id + "';",null);
        return Observable.just(cursor.getCount() != 0);
    }

    @Override
    public Observable<Boolean> isItemAdd(long id) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_FAVORITES_NAME + " WHERE " + KEY_LISTING_ID + " = '" + id + "';",null);
        return Observable.just(cursor.getCount() != 0);
    }

    @Override
    public void removeFavorite(long id) {
        try {
            getWritableDatabase().execSQL(String.format("DELETE FROM %1s WHERE %2s = '%3d';", TABLE_FAVORITES_NAME, KEY_LISTING_ID, id));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_FAVORITES_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_LISTING_ID + " INTEGER, "
                + KEY_TITLE + " TEXT, "
                + KEY_ITEM_DESCRIPTION + " TEXT, "
                + KEY_PRICE + " REAL, "
                + KEY_PRICE_DESCRIPTION + " TEXT, "
                + KEY_CATEGORY + " TEXT, "
                + KEY_IMAGE_URL_MEDIUM + " TEXT, "
                + KEY_IMAGE_URL_LARGE + " TEXT, "
                + KEY_IMAGE_URL_FULL + " TEXT);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES_NAME);
        onCreate(db);
    }

    private String getDataFromItem(ItemDTO item) {

        return String.format("%1d, '%2s', '%3s', '%4f', '%5s', '%6s', '%7s', '%8s', '%9s'",
                item.getListingId(), item.getTitle(), item.getDescription(), item.getPrice()
                , item.getPriceDescription(), item.getCategory(), item.getImageMedium(), item.getImageBig(), item.getImageFull());
    }

    private ItemDTO getItemFromCursor(Cursor cursor){
        ItemDTO item = new ItemDTO();
        item.setListingId(cursor.getLong(1));
        item.setTitle(cursor.getString(2));
        item.setDescription(cursor.getString(3));
        item.setPrice(cursor.getFloat(4));
        item.setPriceDescription(cursor.getString(5));
        item.setCategory(cursor.getString(6));
        item.setImageMedium(cursor.getString(7));
        item.setImageBig(cursor.getString(8));
        item.setImageFull(cursor.getString(9));
        item.setFavorites(true);
        return item;
    }

/*
*
INSERT INTO favorites (listing_id, title, description, price, price_description, category, image_medium, image_large, image_full) VALUES (236567158, 'Sweat Like A Pig To Look Like A Fox Personal Best T-Shirt - Funny Slogan tshirt tee gift running exercise healthy training trainers fit 123t', 'An inspirational Personal Best® original design that motivates anyone sweating their way to a hot body to keep on sweating like a pig to look like a fox. Makes an ideal gift for running, fitness, training, and gym obsessed friends and family members.
Whether for Dad or Uncle, your partner or even your friends, our &quot;Sweat Like A Pig To Look Like A Fox&quot; design from our Personal Best range is sure to put a smile on their face. After all, we all know someone who this slogan would apply to!
UK sizes in inches (Chest to fit) S - 36&quot;, M - 40&quot;, L - 44&quot;, XL - 48&quot;, 2XL* - 52&quot;, 3XL* - 56&quot;, 4XL - 60&quot;, 5XL - 64&quot;
S - 2XL All colours available
3XL - 5XL Available in Black, Navy, Red and Royal Blue
This design is also available in Sweatshirts, Hoodies and even mugs!
** Please contact us if you would like this in Kids&#39; Sizes **
This is a professionally produced screen-printed product and make wonderful gifts for every occasion.
Although cheaper items may be available elsewhere, the quality of our garments and production techniques are unparalleled. So while the price may be a little more expensive, we&#39;re adamant our assurance of quality is worth spending that little bit extra. We operate a 100% CUSTOMER SATISFACTION POLICY', 7,970000, '  GBP', 'category', 'https://img0.etsystatic.com/066/0/11007521/il_170x135.785238970_abce.jpg', 'https://img0.etsystatic.com/066/0/11007521/il_570xN.785238970_abce.jpg', 'https://img0.etsystatic.com/066/0/11007521/il_fullxfull.785238970_abce.jpg');
*/

}
