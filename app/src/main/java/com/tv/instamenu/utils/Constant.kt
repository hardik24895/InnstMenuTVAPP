package com.tv.instamenu.utils


object Constant {


    // const val BASE_URL = "http://societyfy.in/lightmf/"
    // const val BASE_URL = "http://societyfy.in/lightmf_stagging/"


   // private const val BASE_URL = "https://beta.sniffspace.com.au/newsite/"
    private const val BASE_URL = "https://beta.sniffspace.com.au/development3/sniffspace-php-2022/"
    const val API_URL = "${BASE_URL}api/"
    const val HARMONY_API_URL = "https://hosted.mastersoftgroup.com/harmony/rest/au/address"
    const val TERM_SERVICE_URL ="https://beta.sniffspace.com.au/development3/sniffspace-php-2022/terms-of-service"
    const val PRIVACY_POLICY_URL ="https://beta.sniffspace.com.au/development3/sniffspace-php-2022/privacy-and-cookie-policy"

    const val FLATNO = "flatno"
    const val ADDRESS = "address"
    const val MAP_RADIOUS = "mapRadious"
    const val DOG_SIZE = "dogSize"
    const val DOGSIZE = "dogsize"
    const val FENCING_TYPE = "fencingType"
    const val FENCING_HEIGHT = "fenching_height"
    const val BEHAVOUR = ":behaviour"
    const val PETS = "propertyotherpets"
    const val AVAILABILITY = "availability"
    const val PETSVISIBILITY = "propertyotherpetsvisibility"
    const val PEOPLEVISIBILITY = "propertypeoplevisibility"
    const val AMENITES = "amenities"
    const val ACTIVITY = "activity"
    const val CONTECTLESS = "contactlessentry"
    const val STREETNAME = "streetname"
    const val STREETNO = "streetnumber"
    const val POSTCODE = "postcode"
    const val SUBURB = "suburb"
    const val STREET = "street"
    const val STATE = "state"
    const val FORGPWD_URL="${BASE_URL}forgotpassword"

    // Common Params
    const val ACTION = "action"
    const val PARAMS = "param"
    const val ERROR = "error"
    const val DATA = "data"
    const val TITLE = "title"
    const val TYPE = "type"
    const val SDATE = "sdate"
    const val EDATE = "edate"
    const val TEXT = "text"
    const val QTY = "qty"
    const val INFO = "info"
    const val IS_CLEAR_ALL = "isclearall"
    const val PAGE_COUNT = "10"
    const val EDIT = "Edit"
    const val REMOVE = "Remove"
    const val DETAIL = "Detail"

    //Menu List
    const val  PROFILE= "Profile"
    const val  DOGS= "Dogs"
    const val  SPACES= "Spaces"
    const val  ALERTS= "Alerts"
    const val  FINACIALS= "Finacials"
    const val  INVITES= "Invites"
    const val  QUESTIONS= "Question"
    const val  CALENDERS= "Calender"
    const val  REVIEWS= "Reviews"
    const val  BOOKINGS= "Bookings"
    const val  LOGOUTS= "Logout"


    /**
     * Times in long
     * */
    const val CONNECT_TIMEOUT = 70
    const val READ_TIMEOUT = 70


    //Google key
  //  const val GOOGLE_KEy="627561253435-mqr1k1uqfag32pcnbcfcisbhg88u79cv.apps.googleusercontent.com"
   // const val GOOGLE_KEy="1048201037469-lq620jp766sdfsfpo65e9ivjse6ghj7l.apps.googleusercontent.com"
    const val GOOGLE_KEy="1048201037469-dskbehhk7nlpohvhimoo7jr2s6hcvbno.apps.googleusercontent.com"


    // ---Server Date Time--//
    const val DATE_FORMAT = "yyyy-MM-dd"



    const val ORDER_ID = "orderId"
    const val UNAUTHORIZED = "unauthorized"


    /**
     * PARAMETERS
     **/
    const val PAGE = "page"
    const val PAGENO = "pageno"
    const val IS_PAGINATION = "is_pagination"
    const val LIMIT = "limit"
    const val SESSION_KEY ="sessionKey"
    const val USER_ID ="user_id"
    const val USERID ="userId"
    const val SCREENID ="screenId"
    const val ID ="id"
    const val EMAIL ="email"
    const val PASSWORD ="password"
    const val DEVICE_TOKEN ="devicetoken"
    const val DEVICE_TYPE ="devicetype"
    const val TIMEZONE ="timezone"
    const val DEVICE_ID ="deviceid"
    const val ACCESS_KEY ="accesskey"
    const val LOGIN_TYPE ="logintype"
    const val FULL_NAME ="fullname"
    const val REQ_TOKEN ="reqtoken"
    const val ACCOUNT_HOLDER ="account_holder"
    const val BANK_NAME ="bank_name"
    const val BSB_CODE ="bsb_code"
    const val ACCOUNT_NO ="account_number"
    const val CONFIRM_ACCOUNT_NO ="confirm_account_number"
    const val DEFAULT_METHOD ="default_method"
    const val BANKID ="bankid"
    const val LASTID ="lastid"
    const val DOGID ="dogid"
    const val STATUS ="status"
    const val SIZE ="size"
    const val NAME ="name"
    const val MICROCHIPNUMBER ="microchipnumber"
    const val DESCRIPTION ="description"
    const val GENDER ="gender"
    const val BREED ="breed"
    const val WEIGHT ="weight"
    const val BEHAVIOURTRAITS ="behaviourtraits"
    const val FAVOURITEACTIVITIES ="favouriteactivities"
    const val VACCDETAILSURL ="vaccdetailsurl"
    const val IMAGE_MAIN_URL ="image_main_url"
    const val IMAGE_THUMB_URL ="image_thumb_medium"
    const val VACCOCTYPE ="vaccdoctype"
    const val DOGWAIVER ="dogwaiver"
    const val ENERGYLEVEL ="energylevel"
    const val TRANING_LEVEL ="training_level"
    const val BIRTHDATE ="birthdate"
    const val VACCINATIONDATE ="vaccinationdate"
    const val DESEXED ="desexed"
    const val SOCIALISATION ="socialisation"
    const val SPACEID ="spaceid"
    const val ALERT_ADDRESS ="alert_address"
    const val LATLONG ="latlong"
    const val ALERT_NAME ="alert_name"
    const val POSTCODE_SITANCE ="postcode_distance"
    const val ALERTID ="alertid"
    const val ADDRESS_TYPE ="addresstype"
    const val OTHER ="other"
    const val FLAT_NUMBER ="flatNumber"
    const val STREETTYPE ="streetType"
    const val CITY ="city"
    const val SHORTDESC ="shortdesc"
    const val LONGDESC ="longdesc"
    const val SPACESIZE ="spacesize"
    const val SPACESIZEUNIT ="spacesizeunit"
    const val FENCING ="fencing"
    const val FENCINGHEIGHT ="fencingheight"
    const val FENCINGTYPE ="fencingtype"
    const val ESCAPE_PROOF ="escape_proof"
    const val CONTACTLESSENTRY ="contactlessentry"
    const val ACCESSINSTRUCTIONS ="accessinstructions"
    const val BOOKING_INSTRUCTION ="booking_instruction"
    const val PROPERTYOTHERPETS ="propertyotherpets"
    const val PROPERTYOTHERPETSVISIBILITY ="propertyotherpetsvisibility"
    const val PROPERTYOTHERPETSDETAILS ="propertyotherpetsdetails"
    const val PROPERTYPEOPLE ="propertypeople"
    const val PROPERTYPEOPLEVISIBILITY ="propertypeoplevisibility"
    const val PROPERTYPEOPLEDETAILS ="propertypeopledetails"
    const val SURANIMALS ="suranimals"
    const val SURANIMALSDETAILS ="suranimalsdetails"
    const val SURPEOPLE ="surpeople"
    const val SURPEOPLEDETAILS ="surpeopledetails"
    const val MAXDOGS ="maxdogs"
    const val TIMEPERBOOKING ="timeperbooking"
    const val HOURRATE ="hourrate"
    const val NO_OF_DOGS_ALLOWED ="no_of_dogs_allowed"
    const val ADDITIONALDOGRATE ="additionaldograte"
    const val ADDITIONAL_PRICE ="additional_price"
    const val ISDRAFT ="isdraft"
    const val PHOTOURLS ="photourls"
    const val PHOTOTHUMB ="photothumb"
    const val ACTIVITIES ="activities"
    const val AMENITIES ="amenities"
    const val THINGSTOCONSIDER ="thingstoconsider"
    const val ACKNOWLEDGE ="acknowledge"
    const val ACCEPTWAIVERHDN ="acceptWaiverhdn"
    const val FULLADDRESS ="fullAddress"
    const val PAYLOAD ="payload"
    const val SOURCEOFTRUTH ="sourceOfTruth"
    const val QID ="qid"
    const val REDESCPRTION ="redescription"
    const val MOBCHECK ="mobCheck"
    const val MOBILEVERIFICATION ="mobileverification"
    const val PHOTOURL ="photourl"
    const val THUMBURL ="thumburl"
    const val MOBILE ="mobile"
    const val FIRSTNAME ="firstname"
    const val MIDDLENAME ="middlename"
    const val LASTNAME ="lastname"
    const val ACCOUNT_TYPE ="account_type"
    const val MONTH ="month"
    const val YEAR ="year"
    const val DAYS ="days"
    const val FIRST_AVAILBLE_DATE ="first_available_date"
    const val DAY ="day"
    const val START_TIME ="start_time"
    const val END_TIME ="end_day"
    const val DATE ="date"
    const val ISUNVAILABLE ="isunavilable"
    const val UNVAILABLE ="unavilable"
    const val REQTOKEN ="reqtoken"
    const val TERM ="term"
    const val HOST ="host"
    const val SEARCH_LAT_LONG ="search_lat_lng"
    const val MAP_RADIUSS ="map_radius"
    const val FENCING_TYPES ="fenching_type"

    //Account Type
    const val DOG= "dog"
    const val SPACE= "space"
    const val BOTH= "both"

    // Date Formate
    const val DD_MM_YYYY ="dd/MM/yyyy"
    const val DD_MMM_YYYY ="dd MMM yyyy"
    const val YYYY_MM_DD ="yyyy-MM-dd"


    // Font Path
    const val REGULAR_FONT ="font/Nunito-Regular.ttf"
    const val BOLD_FONT ="font/Nunito-Bold.ttf"
    const val LIGHT_FONT ="font/Nunito-Light.ttf"
    const val MEDIUM_FONT ="font/Nunito-Medium.ttf"
    const val SEMI_BOLD_FONT ="font/Nunito-SemiBold.ttf"





    //--- Action Name-----

    const val LOGIN = "login"
    const val LOGOUT = "logout"
    const val SCREEN = "screens"
    const val MENULIST = "menuListapi"




}