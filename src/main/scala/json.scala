package json

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

object States {

  @State(Scope.Benchmark)
  class CommonState {
    val pageInfo: model.pageInfo.PageInfoRes = {
      import model.pageInfo._

      val layoutDynamicConfiguration = LayoutDynamicConfiguration(
        hideHeader = false,
        addLanguageToUrl = false,
        tagAsNew = false,
        adobePageTitle = "adobePageTitle",
        backgroundConfiguration = BackgroundConfiguration(
          backgroundImage = "backgroundImage",
          backgroundColor = "backgroundColor",
          backgroundFixed = false
        ),
        canonicalTagConfiguration = CanonicalTagConfiguration(
          useLayout = true,
          layout = "layout",
          customUrl = "customUrl",
          localeToUrl = Map(
            "en_US" -> "/qwe123_en",
            "de_DE" -> "/qwe123_de"
          )
        ),
        orgName = "orgName"
      )

      val codeInsertion = CodeInsertion(onPageRendering = "onPageRendering", onPageRendered = "onPageRendered", rootClassNames = "rootClassNames")

      def portletInfo(c: Option[Config]) = PortletInfo(
        portletType = "portletType",
        order = "order",
        portletId = "portletId",
        column = "column",
        title = "title",
        showBorders = false,
        customCSS = "customCSS",
        customCSSClassName = "customCSSClassName",
        showMinIcon = false,
        initialWindowState = "initialWindowState",
        enabledDevices = Map(
          "desktop" -> true,
          "mobile" -> true,
          "tablet" -> true
        ),
        config = c
      )

      val gameConfig = Game.GameConfig(
        additionalQueryString = "additionalQueryString",
        animatedGifDuration = 123.456,
        autoFill = true,
        automaticContentLoading = true,
        categoriesCookieName = "categoriesCookieName",
        categoriesFeedName = "categoriesFeedName",
        cookiePrefix = "cookiePrefix",
        disableDemoWhenLoggedIn = true,
        disableLazyLoading = true,
        dynamicFeaturedCategory = true,
        enableAnimatedGif = true,
        enableDyUnitTracking = true,
        enableUserProfile = true,
        favoritesLayout = "favoritesLayout",
        favoritesLocation = Game.NoneLocation,
        filterChangePage = true,
        formFactor = "formFactor",
        gamesNumberOfColumns = 123456789,
        gamesNumberOfRows = 123456789,
        gameTypes = List("gameType1", "gameType2", "gameType3", "gameType4", "gameType5"),
        includeFavoritesCategory = true,
        itemView = Game.Modern,
        listViewOptionEnabled = true,
        navigationPosition = Game.Top,
        navigationStyle = Game.Menu,
        navigationType = Game.Slider,
        navigationWidth = 123456789,
        numAdvisoryGamesForGuest = 123456789,
        numAdvisoryGamesForPlayer = 123456789,
        numFavoritesAdvisoryGamesForPlayer = 123456789,
        numFavoritesForPlayer = 123456789,
        numTopGamesForGuest = 123456789,
        onlyFavorites = true,
        profileFeeds = List(
          Game.ProfileFeedModel(name = "name1", urltemplates = "urltemplates1"),
          Game.ProfileFeedModel(name = "name2", urltemplates = "urltemplates2"),
          Game.ProfileFeedModel(name = "name3", urltemplates = "urltemplates3")
        ),
        recentlyLayout = "recentlyLayout",
        refineResetOption = true,
        responsiveGrid = true,
        showGuaranteedHitTime = true,
        showJackpots = true,
        showNavigation = true,
        showOnly = true,
        showOnlyType = Game.All,
        showSearch = true,
        showShortDescription = true,
        sortBy = true,
        useFavoritesEnabled = true,
        useRecentlyEnabled = true,
        viewMode = Game.ItemView,
      )

      val banner = Banner.Banner(
        bannerEnabled = true,
        bannerTitle = "bannerTitle",
        profileIDs = "profileIDs",
        contents = List(
          Banner.BannerContent(bannerUrl = "bannerUrl1", gameCode = "gameCode1", linkFromBanner = "linkFromBanner1", bannerType = Banner.Html, bannerHtml = (1 to 200).mkString, webContentId = "webContentId1", articleVersion = Some("articleVersion1"), linkImageType = Banner.NoneLinkImageType, cid = "cid1"),
          Banner.BannerContent(bannerUrl = "bannerUrl2", gameCode = "gameCode2", linkFromBanner = "linkFromBanner2", bannerType = Banner.Html, bannerHtml = (200 to 300).mkString, webContentId = "webContentId2", articleVersion = Some("articleVersion2"), linkImageType = Banner.NoneLinkImageType, cid = "cid2"),
          Banner.BannerContent(bannerUrl = "bannerUrl3", gameCode = "gameCode3", linkFromBanner = "linkFromBanner3", bannerType = Banner.Html, bannerHtml = (300 to 400).mkString, webContentId = "webContentId3", articleVersion = Some("articleVersion3"), linkImageType = Banner.NoneLinkImageType, cid = "cid3")
        ),
        affiliatesEnabled = Some(true),
        affiliatesList = Option("affiliatesList"),
        locales = "locales",
        formFactor = List("tablet", "mobile", "desktop"),
        groupId = "groupId",
        showForGuests = true,
        showForLoggedIn = true,
        showForLoggedOut = true,
        guestsLanguage = "guestsLanguage",
        guestsCountry = "guestsCountry",
        guestsAffiliate = "guestsAffiliate",
        isIncludeGuestLanguage = true,
        isIncludeGuestCountry = true,
        isIncludeGuestAffiliate = true,
        loggedInType = "loggedInType",
      )

      val bannerConfig = Banner.BannerConfig(
        affiliateCookieName = "affiliateCookieName",
        arrowPosition = Some("arrowPosition"),
        arrowPositionUnit = Some("arrowPositionUnit"),
        bannerGroups = List(
          Banner.BannerGroup(groupId = "groupId1", groupName = "groupName1", segmentationType = Some("segmentationType1"), urltemplates = Some("urltemplates1")),
          Banner.BannerGroup(groupId = "groupId2", groupName = "groupName2", segmentationType = Some("segmentationType2"), urltemplates = Some("urltemplates2")),
          Banner.BannerGroup(groupId = "groupId3", groupName = "groupName3", segmentationType = Some("segmentationType3"), urltemplates = Some("urltemplates3"))
        ),
        banners = List(
          banner,
          banner,
          banner,
          banner,
          banner
        ),
        bulletsOffsetX = Some("bulletsOffsetX"),
        bulletsOffsetY = Some("bulletsOffsetY"),
        bulletsPositionX = Some("bulletsPositionX"),
        bulletsPositionY = Some("bulletsPositionY"),
        customBulletsPos = true,
        customImageSize = Some("customImageSize"),
        disableLazyLoading = Some("disableLazyLoading"),
        displayLoadingBar = Some("displayLoadingBar"),
        dyUnitName = Some("dyUnitName"),
        enableDyUnitTracking = true,
        enableReact = true,
        height = "height",
        heightUnit = "heightUnit",
        interfaceOptions = "interfaceOptions",
        layoutStyle = "layoutStyle",
        loadingBarAlignment = Some("loadingBarAlignment"),
        loadingBarColour = Some("loadingBarColour"),
        loadingBarHeight = Some("loadingBarHeight"),
        loadingBarOpacity = Some("loadingBarOpacity"),
        loadingBarWidth = Some("loadingBarWidth"),
        numberOfBanners = Some("numberOfBanners"),
        timeToDisplay = "timeToDisplay",
        transitionType = "transitionType",
      )

      PageInfoOk(
        success = true,
        error = "",
        colorSchemeCSSClass = "colorSchemeCSSClass",
        permitted = true,
        name = "name",
        portlets = List(
          portletInfo(Some(bannerConfig)),
          portletInfo(Some(gameConfig)),
          portletInfo(None),
          portletInfo(Some(bannerConfig)),
        ),
        translations = (1 to 2000).map { n =>
          (n to 15).mkString -> (n to 150).mkString
        }.toMap,
        friendlyURL = "friendlyURL",
        title = "title",
        customCss = "customCss",
        layoutType = "layoutType",
        pageLayoutId = "pageLayoutId",
        footer = "footer",
        layoutDynamicConfig = layoutDynamicConfiguration,
        permissions = Map(
          "guest" -> true,
          "player" -> true
        ),
        codeInsertion = codeInsertion,
        showAsPopUp = false,
        popupWidth = 123,
        description = "description",
        keywords = "keywords",
        robots = "robots"
      )
    }

    val objectMapper = {
      val mapper = new com.fasterxml.jackson.databind.ObjectMapper()
      mapper.registerModule(com.fasterxml.jackson.module.scala.DefaultScalaModule)
      mapper
    }

    val jsoniterCodec = {
      import com.github.plokhotnyuk.jsoniter_scala.macros._
      import com.github.plokhotnyuk.jsoniter_scala.core._
      import model.pageInfo._
      val codec: JsonValueCodec[PageInfoRes] = JsonCodecMaker.make[PageInfoRes](CodecMakerConfig())
      codec
    }
  }
}

class PageInfoEncode {
  import States._

  @Benchmark
  def jacksonEncodeAsBytes(state: CommonState): Unit = {
    state.objectMapper.writeValueAsBytes(state.pageInfo)
  }

  @Benchmark
  def jacksonEncodeAsString(state: CommonState): Unit = {
    state.objectMapper.writeValueAsString(state.pageInfo)
  }

  @Benchmark
  def jacksonEncodeAsStringToBytes(state: CommonState): Unit = {
    state.objectMapper.writeValueAsString(state.pageInfo).getBytes("UTF-8")
  }

  @Benchmark
  def jsoniterEncode(state: CommonState): Unit = {
    import com.github.plokhotnyuk.jsoniter_scala.core._
    writeToArray(state.pageInfo)(state.jsoniterCodec)
  }
}
