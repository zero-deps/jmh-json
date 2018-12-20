package model
package pageInfo

sealed trait PageInfoRes
final case class PageInfoError(error: String, success: Boolean = false) extends PageInfoRes
final case class PageInfoNotPermitted(success: Boolean = true, error: String = "", permitted: Boolean = false) extends PageInfoRes
final case class PageInfoOk(
  success: Boolean = true,
  error: String = "",
  colorSchemeCSSClass: String,
  permitted: Boolean = true,
  name: String,
  portlets: List[PortletInfo],
  translations: Map[String,String],
  friendlyURL: String,
  title: String,
  customCss: String,
  layoutType: String,
  pageLayoutId: String,
  footer: String,
  layoutDynamicConfig: LayoutDynamicConfiguration,
  permissions: Map[String,Boolean],
  codeInsertion: CodeInsertion,
  showAsPopUp: Boolean,
  popupWidth: Int,
  description: String,
  keywords: String,
  robots: String
) extends PageInfoRes

final case class LayoutDynamicConfiguration(
  hideHeader: Boolean,
  addLanguageToUrl: Boolean,
  tagAsNew: Boolean,
  adobePageTitle: String,
  backgroundConfiguration: BackgroundConfiguration,
  canonicalTagConfiguration: CanonicalTagConfiguration,
  orgName: String)

final case class BackgroundConfiguration(
  backgroundImage: String,
  backgroundColor: String,
  backgroundFixed: Boolean)

final case class CanonicalTagConfiguration(
  useLayout: Boolean,
  layout: String,
  customUrl: String,
  localeToUrl: Map[String, String])

final case class CodeInsertion(onPageRendering: String, onPageRendered: String, rootClassNames: String)

final case class PortletInfo(
  portletType: String,
  order: String,
  portletId: String,
  column: String,
  title: String,
  showBorders: Boolean,
  customCSS: String,
  customCSSClassName: String,
  showMinIcon: Boolean,
  initialWindowState: String,
  enabledDevices: Map[String, Boolean],
  config: Option[Config],
)

sealed trait Config

object Banner {
  sealed trait BannerType
  final case object Html extends BannerType
  final case object WebContent extends BannerType
  final case object Image extends BannerType

  sealed trait LinkImageType
  final case object NoneLinkImageType extends LinkImageType
  final case object Url extends LinkImageType
  final case object GameLaunch extends LinkImageType

  final case class BannerContent(
    bannerUrl: String,
    gameCode: String,
    linkFromBanner: String,
    bannerType: BannerType,
    bannerHtml: String,
    webContentId: String,
    articleVersion: Option[String],
    linkImageType: LinkImageType,
    cid: String,
  )

  final case class Banner(
    bannerEnabled: Boolean,
    bannerTitle: String,
    profileIDs: String,
    contents: List[BannerContent],
    affiliatesEnabled: Option[Boolean],
    affiliatesList: Option[String],
    locales: String,
    formFactor: List[String],
    groupId: String,
    showForGuests: Boolean,
    showForLoggedIn: Boolean,
    showForLoggedOut: Boolean,
    guestsLanguage: String,
    guestsCountry: String,
    guestsAffiliate: String,
    isIncludeGuestLanguage: Boolean,
    isIncludeGuestCountry: Boolean,
    isIncludeGuestAffiliate: Boolean,
    loggedInType: String,
  )

  final case class BannerGroup(
    groupId: String,
    groupName: String,
    segmentationType: Option[String],
    urltemplates: Option[String],
  )

  final case class BannerConfig(
    affiliateCookieName: String,
    arrowPosition: Option[String],
    arrowPositionUnit: Option[String],
    bannerGroups: List[BannerGroup],
    banners: List[Banner],
    bulletsOffsetX: Option[String],
    bulletsOffsetY: Option[String],
    bulletsPositionX: Option[String],
    bulletsPositionY: Option[String],
    customBulletsPos: Boolean,
    customImageSize: Option[String],
    disableLazyLoading: Option[String],
    displayLoadingBar: Option[String],
    dyUnitName: Option[String],
    enableDyUnitTracking: Boolean,
    enableReact: Boolean,
    height: String,
    heightUnit: String,
    interfaceOptions: String,
    layoutStyle: String,
    loadingBarAlignment: Option[String],
    loadingBarColour: Option[String],
    loadingBarHeight: Option[String],
    loadingBarOpacity: Option[String],
    loadingBarWidth: Option[String],
    numberOfBanners: Option[String],
    timeToDisplay: String,
    transitionType: String,
  ) extends Config
}

object BingoScheduler {
  final case class BingoSchedulerConfig(
    lobbyName: String,
    portletViewMode: String,
    numbersOfRoom: Int,
    responsive: Boolean,
    numberOfColumns: Int,
    enableViewOptions: Boolean,
    enableFilters: Boolean,
    enableSorting: Boolean,
    viewAllPageLink: String,
  ) extends Config
}

object Bonus {
  sealed trait ViewType
  final case object ImageView extends ViewType
  final case object ListView extends ViewType

  sealed trait WidthUnit
  final case object PxUnit extends WidthUnit
  final case object EmUnit extends WidthUnit

  final case class BonusConfig(
    numberOfRows: Int,
    numberOfColumns: Int,
    viewType: ViewType,
    headerWebContentId: String,
    responsive: Boolean,
    itemWidth: Int,
    itemWidthUnit: WidthUnit,
    bonusCodes: List[String],
  ) extends Config
}

object ContactPreferences {
  final case class PlayerTag(
    name: String,
    `type`: String,
    value: String,
    expirationDate: String,
  )

  final case class ContactPreferencesConfig(
    promotional: List[String],
    account: List[String],
    thirdParty: List[String],
    tagsEnabled: Boolean,
    playerTags: Map[String, PlayerTag],
  ) extends Config
}

object DetailedTransactionHistory {
  final case class DetailedTransactionHistoryConfig(
    showTotalTransactionHistory: Boolean,
    showFeesForTransactions: Boolean,
    showProductForTransactions: Boolean,
    exportEnabled: Boolean,
    statusFilterEnabled: Boolean,
    transactionTypeFilterEnabled: Boolean,
    anotherPeriodEnabled: Boolean,
  ) extends Config
}

object Game {
  sealed trait ViewMode
  final case object ListView extends ViewMode
  final case object ItemView extends ViewMode
  final case object DetailedListView extends ViewMode

  sealed trait ShowOnlyType
  final case object Favorites extends ShowOnlyType 
  final case object Topgames extends ShowOnlyType
  final case object RecentlyPlayed extends ShowOnlyType 
  final case object All extends ShowOnlyType

  sealed trait NavigationType
  final case object Slider extends NavigationType
  final case object ToFit extends NavigationType
  final case object ToFitCategories extends NavigationType

  sealed trait NavigationStyle
  final case object NoneStyle extends NavigationStyle
  final case object RefineBy extends NavigationStyle
  final case object Menu extends NavigationStyle
  final case object RefineAndMenu extends NavigationStyle

  sealed trait NavigationPosition
  final case object Top extends NavigationPosition
  final case object Left extends NavigationPosition

  sealed trait FavoritesLocation
  final case object NoneLocation extends FavoritesLocation
  final case object First extends FavoritesLocation
  final case object Last extends FavoritesLocation

  sealed trait ItemStyleType
  final case object Light extends ItemStyleType
  final case object Extended extends ItemStyleType
  final case object Modern extends ItemStyleType

  final case class ProfileFeedModel(name: String, urltemplates: String)

  final case class GameConfig(
    additionalQueryString: String,
    animatedGifDuration: Double,
    autoFill: Boolean,
    automaticContentLoading: Boolean,
    categoriesCookieName: String,
    categoriesFeedName: String,
    cookiePrefix: String,
    disableDemoWhenLoggedIn: Boolean,
    disableLazyLoading: Boolean,
    dynamicFeaturedCategory: Boolean,
    enableAnimatedGif: Boolean,
    enableDyUnitTracking: Boolean,
    enableUserProfile: Boolean,
    favoritesLayout: String,
    favoritesLocation: FavoritesLocation,
    filterChangePage: Boolean,
    formFactor: String,
    gamesNumberOfColumns: Int,
    gamesNumberOfRows: Int,
    gameTypes: List[String],
    includeFavoritesCategory: Boolean,
    itemView: ItemStyleType,
    listViewOptionEnabled: Boolean,
    navigationPosition: NavigationPosition,
    navigationStyle: NavigationStyle,
    navigationType: NavigationType,
    navigationWidth: Int,
    numAdvisoryGamesForGuest: Int,
    numAdvisoryGamesForPlayer: Int,
    numFavoritesAdvisoryGamesForPlayer: Int,
    numFavoritesForPlayer: Int,
    numTopGamesForGuest: Int,
    onlyFavorites: Boolean,
    profileFeeds: List[ProfileFeedModel],
    recentlyLayout: String,
    refineResetOption: Boolean,
    responsiveGrid: Boolean,
    showGuaranteedHitTime: Boolean,
    showJackpots: Boolean,
    showNavigation: Boolean,
    showOnly: Boolean,
    showOnlyType: ShowOnlyType,
    showSearch: Boolean,
    showShortDescription: Boolean,
    sortBy: Boolean,
    useFavoritesEnabled: Boolean,
    useRecentlyEnabled: Boolean,
    viewMode: ViewMode,
  ) extends Config
}

object MobileCashier {
  sealed trait CashierType
  final case object Deposit extends CashierType
  final case object Withdraw extends CashierType
  final case object PendingWithdrawals extends CashierType
  final case object TransactionHistory extends CashierType
  final case object AddPaymentMethod extends CashierType
  final case object PlayerYearlyStatistic extends CashierType
  final case object DetailedTransactionHistory extends CashierType
  final case object AddPaymentMethodDeposit extends CashierType

  sealed trait TransactionsLayout
  final case object Extended extends TransactionsLayout
  final case object Simple extends TransactionsLayout

  final case class MobileCashierConfig(
    paymentMethodsOrder: String,
    addMethodUrl: String,
    predefinedAmounts: String,
    isShowTotalTransactionHistory: Boolean,
    exportEnabled: Boolean,
    isProductTypeEnabled: Boolean,
    isTransactionTypeEnabled: Boolean,
    isAnotherPeriodEnabled: Boolean,
    isStatusFilterEnabled: Boolean,
    showGroupAs: String,
    transactionsLayout: TransactionsLayout,
    allowDeposit: Boolean,
    suggestDepositAmounts: Boolean,
    hideParentPaymentMethods: Boolean,
    cashierType: CashierType,
  ) extends Config
}

object IframeCashier {
  final case class IframeCashierConfig(
    cashierWidth: String,
    cashierWidthUnit: String,
    cashierHeight: String,
    cashierHeightUnit: String,
    cashierServiceType: String,
    cashierCashierPageID: String,
  ) extends Config
}

object Registration {
  sealed trait TagType
  final case object Text extends TagType
  final case object Number extends TagType
  final case object Date extends TagType

  final case class Tag(
    name: String,
    `type`: TagType,
    value: String,
    expirationDate: String,
  )

  final case class RegistrationConfig(
    layoutStyle: String,
    registrationRetryCount: String,
    defaultCountry: Option[String],
    afterRegistrationAction: String,
    eighteenPlusWebContentsID: String,
    termsAndConditionsId: String,
    verificationQuestionIDs: Option[String],
    tcversion: String,
    clienttype: Option[String],
    customFieldNumber: Option[Int],
    customFieldValue: Option[String],
    creferer: Option[String],
    downloadClientRegistration: Boolean,
    providedByVFPromotionSystem: Boolean,
    includeSerial: Boolean,
    useEmailAsUsername: Boolean,
    depositLimitEnabled: Boolean,
    advancedContactEnabled: Boolean,
    duplicateEmailLookup: Boolean,
    contractWebContentID: Option[String],
    privacyPolicyWebContentID: Option[String],
    formCookieName: Option[String],
    selectedRegForm: Option[String],
    depositLimitDailyDefault: Option[Int],
    depositLimitWeeklyDefault: Option[Int],
    depositLimitMonthlyDefault: Option[Int],
    depositLimitInputType: Option[String],
    customCitizenship: Option[String],
    enablePlayerTags: Boolean,
    tagsConfiguration: List[Tag],
  ) extends Config
}

object ResponsibleGaming {
  final case class ResponsibleGamingConfig(
    depositLimits: Boolean,
    timeout: Boolean,
    selfExclusion: Boolean,
    react: Boolean,
    inactivityTimeout: Boolean,
    realityCheck: Boolean,
    betLimits: Boolean,
    lossLimits: Boolean,
    sessionTimer: Boolean,
  ) extends Config
}

object WebContent {
  final case class WebContentConfig(
    id: Option[String],
  ) extends Config
}
