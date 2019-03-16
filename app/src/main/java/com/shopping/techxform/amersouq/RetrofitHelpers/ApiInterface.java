package com.shopping.techxform.amersouq.RetrofitHelpers;

//import info.androidhive.retrofit.model.MoviesResponse;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.shopping.techxform.amersouq.Models.AddProductResponse;
import com.shopping.techxform.amersouq.Models.AlertResponse;
import com.shopping.techxform.amersouq.Models.CreateProductInputModel;
import com.shopping.techxform.amersouq.Models.FeedbackResponse;
import com.shopping.techxform.amersouq.Models.ProductRequest;
import com.shopping.techxform.amersouq.Models.ProductsResponse;
import com.shopping.techxform.amersouq.Models.ProfileResponse;
import com.shopping.techxform.amersouq.Models.ProfileUpdateRequest;
import com.shopping.techxform.amersouq.Models.ProfileUpdateResponse;
import com.shopping.techxform.amersouq.Models.ViewSingleProductResponse;
import com.shopping.techxform.amersouq.Models.cart.DeleteCartInput;
import com.shopping.techxform.amersouq.Models.cart.UpdateCartInput;
import com.shopping.techxform.amersouq.Models.cart.UpdateCartOutput;
import com.shopping.techxform.amersouq.Models.cart.ViewCartResponse;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AdCoordinatesOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AdLocationModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AddItemToCartRequest;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AddProduct;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.AttributeAddInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.CreateAuctionInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.CreateClassifiedInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.CreateFixedInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.OptionAddInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.attribute_product.AttributeProductInput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.attribute_product.AttributeProductOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.InputModels.bidinput.BidInputModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AddToCartResponse;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AllAdsOutput.ViewAllAdsOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Auction.OptionAddOutput_Auc;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Classified.OptionAddOutput_Classified;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.All_Ads_Fixed.OptionAddOutput_Fixed;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput.TopBidOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.AuctionOutput.ViewAuctionOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryForsaleData.CategoryForsaleOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CategoryOutput.CategoriesOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ClassifiedData.ViewClassifiedOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ClassifiedTypeData.ClassifiedTypeOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.Classified_Ad_Update.Classs_Ad_Update;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ConditionsData.ConditionsOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.CreateAd.CreateClassifiedOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.DeleteWishData.DeleteWishModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.Disable_Ad.OptionAddOutput_Disable;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.FixedPriceOutput.ViewFixedPriceOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.HomePageData.HomepageOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LanguageData.LanguagesOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LocationsData.LocationsOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.LoginOutputModule.LoginOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.OptionOutput.OptionAddOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ProductImageUpload.ImageUploadOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ProductOutputModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.SearchData.SearchOuputModel;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.ShippingPackage.ShippingPackageOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.WishOutput.WishOutputList;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_output.AttributeAddOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.attribute_set.AttributeSetOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.bidoutput.BidOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.profile_image_out.ProfileImageOutput;
import com.shopping.techxform.amersouq.RetrofitHelpers.Models.savefcmOutput.SavefcmOutput;

import org.json.JSONObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("login/")
    Call<LoginOutput> login_service(@Body LoginInput loginInput);

    @POST("signup/")
    Call<ResponseBody> signup_service(@Body SignUpInput signUpInput);


    @GET("get_categories.php")
    Call<CategoriesOutput> get_categories_ad(@Query("main_cat") String main_cat);


    @GET("get_language.php")
    Call<LanguagesOutput> get_languages();

    @GET("get_locations.php")
    Call<LocationsOutput> get_locations();


    @GET("get_conditions.php")
    Call<ConditionsOutput> get_conditions();

    @GET("get_shippingpackage.php")
    Call<ShippingPackageOutput> get_shippingpackage();

    @POST("products/add_attribute.php")
    Call<AttributeAddOutput> attribute_add(@Body AttributeAddInput attributeAddInput);

    @POST("products/add_option_attribute.php")
    Call<OptionAddOutput> attribute_option_add(@Body OptionAddInput optionAddInput);


    @GET("save_fcm_token.php")
    Call<SavefcmOutput> save_fcm_token(@Query("userid") String userid, @Query("regtoken") String fcm_token, @Query("imei") String imei);

    @GET("get_classified_type.php")
    Call<ClassifiedTypeOutput> get_classified_types();

//    @GET("get_myads.php")
//    Call<MyAdsOutput> get_my_ads(@Query("created_user_id") String user_id, @Query("page_number") String page_no);
//
//    @GET("get_all_ads.php")
//    Call<MyAdsOutput> get_view_ads(@Query("page_number") String page_no);

    @GET("get_ads_bycat.php")
    Call<MyAdsOutput> get_category_ads(@Query("category_id") String cat_id, @Query("page_number") String page_no);

    @GET("get_all_ads.php")
    Call<OptionAddOutput_Auc> get_category_ads_Auc(@Query("type") String cat_id, @Query("page_number") String page_no, @Query("latitude") String latitude, @Query("longitude") String longitude);

    @GET("get_all_ads.php")
    Call<OptionAddOutput_Fixed> get_category_ads_Fixed(@Query("type") String cat_id, @Query("page_number") String page_no);

    @GET("get_all_ads.php")
    Call<OptionAddOutput_Classified> get_category_ads_Classified(@Query("type") String cat_id, @Query("page_number") String page_no);


    @GET("get_classified_single_ad.php")
    Call<ViewClassifiedOutput> get_classified(@Query("ad_id") String ad_id);

    @GET("products/delete_ad.php?")
    Call<OptionAddOutput_Disable> delete_ad_classified(@Query("ad_id") String ad_id);

    @GET("get_single_auction_ad.php")
    Call<ViewAuctionOutput> get_auction(@Query("ad_id") String ad_id);

    @GET("get_single_fixed_ad.php")
    Call<ViewFixedPriceOutput> get_fixedprice(@Query("ad_id") String ad_id);

    @GET("get_forsale_child.php")
    Call<CategoryForsaleOutput> get_forsale_child();


    @POST("add_classified.php")
    Call<CreateClassifiedOutput> create_classified(@Body CreateClassifiedInput createClassifiedInput);

    @POST("add_fixed_price.php")
    Call<CreateClassifiedOutput> create_fixed(@Body CreateFixedInput createFixedInput);

    @POST("add_auction.php")
    Call<CreateClassifiedOutput> create_auction(@Body CreateAuctionInput createAuctionInput);

    @Multipart
    @POST("product_image_upload.php")
    Call<ImageUploadOutput> upload_ad_image(@Part("ad_id") RequestBody ad_id, @Part MultipartBody.Part fileToUpload);

    @Multipart
    @POST("products/product_image_upload.php")
    Call<ImageUploadOutput> upload_product_image(@Part("product_id") RequestBody product_id, @Part MultipartBody.Part fileToUpload);


    @Multipart
    @POST("products/update_profile_image.php")
    Call<ProfileImageOutput> upload_profile_image(@Part("user_id") RequestBody user_id, @Part MultipartBody.Part fileToUpload);

    @POST("add_location.php")
    Call<AdCoordinatesOutput> update_ad_location(@Body AdLocationModel adLocationModel);

    @POST("mp_catalogue/")
    Call<JsonObject> add_product(@Body AddProduct addProduct);

    @POST("add_new_product.php?")
    Call<JsonObject> addNewProduct(@Body AddProduct addProduct);

    @GET("mp_catalogue/{id}")
    Call<JsonObject> getProductDetail(@Path("id") int product_id);

    @GET("products/view_all_products.php?")
    Call<ProductsResponse> getProductList(@Query("category_id") int cat_id, @Query("pagenum") int page_no);

    @GET("products/view_profile.php?")
    Call<ProfileResponse> getProfileDetails(@Query("userid") int user_id);

    @POST("products/update_profile.php?")
    Call<ProfileUpdateResponse> updateProfileDetails(@Body ProfileUpdateRequest profileUpdateRequest);

    @POST("products/add_new_product.php?")
    Call<JsonObject> addNewProductNew(@Body ProductRequest addProduct);

    @GET("products/view_my_ads.php?")
    Call<JsonObject> viewAdds(@Query("userid") int userid, @Query("pagenum") int page_no);

    @GET("products/view_package.php?")
    Call<JsonObject> view_packages(@Query("userid") int userid);

    @GET("products/view_feedback.php?")
    Call<FeedbackResponse> view_feedback(@Query("userid") int userid, @Query("pagenum") int page_no);

    @GET("products/view_alerts.php?")
    Call<AlertResponse> view_alerts();

    @GET("products/view_homepage.php")
    Call<HomepageOutput> view_homepage();

    @GET("products/view_cart.php")
    Call<ViewCartResponse> view_cart(@Query("user_id") int userid);

    @POST("products/add_cart.php?")
    Call<AddToCartResponse> add_product_to_cart(@Body AddItemToCartRequest addItemToCartRequest);

    @GET("products/view_single_product.php?")
    Call<ViewSingleProductResponse> view_single_product(@Query("product_id") String productId, @Query("pagenum") int page_no);

    @GET("products/update_classified1.php?")
    Call<Classs_Ad_Update> update_class_profile(@Query("id") String id, @Query("title") String title,
                                                @Query("short_description") String short_description, @Query("description") String description,
                                                @Query("ad_visibility") String ad_visibility, @Query("price") String price,
                                                @Query("offer_price") String offer_price, @Query("address_info") String address_info, @Query("contact_info") String contact_info,
                                                @Query("condition_id") int condition_id, @Query("locations_id") int locations_id, @Query("classified_type") String classified_type);


    @GET("products/update_fixed1.php?")
    Call<Classs_Ad_Update> update_fixed_profile(@Query("id") String id, @Query("title") String title,
                                                @Query("short_description") String short_description, @Query("description") String description,
                                                @Query("ad_visibility") String ad_visibility, @Query("price") String price,
                                                @Query("offer_price") String offer_price, @Query("address_info") String address_info, @Query("contact_info") String contact_info,
                                                @Query("condition_id") int condition_id, @Query("locations_id") int locations_id, @Query("classified_type") String classified_type);


    @GET("products/update_auction1.php?")
    Call<Classs_Ad_Update> update_auction(@Query("id") String id, @Query("title") String title,
                                                @Query("short_description") String short_description, @Query("description") String description,
                                          @Query("is_homepage_live") String is_homepage_live,@Query("featured") String featured);


    @GET("products/view_top_bid.php")
    Call<TopBidOutput> get_auction_bids(@Query("auction_id") String auction_id);


    @POST("products/add_bid.php")
    Call<BidOutput> set_auction_bids(@Body BidInputModel bidInputModel);


    @POST("products/add_pro.php")
    Call<ProductOutputModel> add_pro(@Body CreateProductInputModel inputModel);


    @GET("products/fetch_attribute_set.php")
    Call<AttributeSetOutput> get_attribute_set(@Query("category_id") String category_id);


    @POST("products/save_attributes.php")
    Call<AttributeProductOutput> set_product_attributes(@Body List<AttributeProductInput> attributeProductInputs);

    @POST("products/update_cart.php")
    Call<UpdateCartOutput> update_cart(@Body UpdateCartInput updateCartInput);

    @POST("products/delete_cart.php")
    Call<UpdateCartOutput> delete_cart(@Body DeleteCartInput deleteCartInput);

    @GET("get_all_ads_search.php")
    Call<SearchOuputModel> get_search_items();

    @GET("view_wishlist.php")
    Call<WishOutputList> get_wish_list(@Query("user_id") String user_id, @Query("page_number") String page_no);


    @GET("remove_wishlist.php")
    Call<DeleteWishModel> remove_wishlist(@Query("wish_id") String wish_id);



}
