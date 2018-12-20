package model

import argonaut._
import argonaut.Argonaut._
import pages._

object pagesCodec {
  implicit val WidgetPrefCodecJson: CodecJson[WidgetPref] = casecodec1(WidgetPref.apply, WidgetPref.unapply)("pref")
  implicit val WidgetPermissionsCodecJson: CodecJson[WidgetPermissions] = casecodec2(WidgetPermissions.apply, WidgetPermissions.unapply)("player", "guest")
  implicit val WidgetLookCodecJson: CodecJson[WidgetLook] = casecodec9(WidgetLook.apply, WidgetLook.unapply)("titles","useCustomTitle","enabledDevices","mobilePortletOrder","showBorders","showMinIcon","initialWindowState","customCSSClassName","customCSS")
  implicit val WidgetConfigCodecJson: CodecJson[WidgetConfig] = casecodec3(WidgetConfig.apply, WidgetConfig.unapply)("look", "permissions", "pref")
  implicit val WidgetCodecJson: CodecJson[Widget] = casecodec3(Widget.apply, Widget.unapply)("id", "version", "config")

  implicit val PermissionsCodecJson: CodecJson[Permissions] = casecodec2(Permissions.apply, Permissions.unapply)("player", "guest")
  implicit val PageSitemapCodecJson: CodecJson[PageSitemap] = casecodec3(PageSitemap.apply, PageSitemap.unapply)("include", "priority", "frequency")
  implicit val PageSeoCodecJson: CodecJson[PageSeo] = casecodec5(PageSeo.apply, PageSeo.unapply)("description", "keywords", "robots", "sitemap", "adobePageTitle")
  implicit val HoverItemCodecJson: CodecJson[HoverItem] = casecodec3(HoverItem.apply, HoverItem.unapply)("title", "wc_id", "enabled")
  implicit val MenuMobileCodecJson: CodecJson[MenuMobile] = casecodec4(MenuMobile.apply, MenuMobile.unapply)("hidden", "expandable", "expanded", "quickDeposit")
  implicit val MenuTabletCodecJson: CodecJson[MenuTablet] = casecodec5(MenuTablet.apply, MenuTablet.unapply)("hidden", "expandable", "expanded", "quickDeposit", "navigationBar")
  implicit val MenuDesktopCodecJson: CodecJson[MenuDesktop] = casecodec5(MenuDesktop.apply, MenuDesktop.unapply)("hidden", "hoverMenu", "hoverItems", "quickDeposit", "navigationBar")
  implicit val PageMenuConfigCodecJson: CodecJson[PageMenuConfig] = casecodec6(PageMenuConfig.apply, PageMenuConfig.unapply)("cssClasses", "icon", "target", "mobile", "tablet", "desktop")
  implicit val AppearanceCodecJson: CodecJson[Appearance] = casecodec5(Appearance.apply, Appearance.unapply)("hideHeader", "showAsPopup", "popupWidth", "overrideFooter", "footerWcId")
  implicit val PageAppearanceCodecJson: CodecJson[PageAppearance] = casecodec3(PageAppearance.apply, PageAppearance.unapply)("mobile", "tablet", "desktop")
  implicit val CanonicalTagsPageCodecJson: CodecJson[CanonicalTagsPage] = casecodec1(CanonicalTagsPage.apply, CanonicalTagsPage.unapply)("pageId")
  implicit val CanonicalTagsUrlCodecJson: CodecJson[CanonicalTagsUrl] = casecodec1(CanonicalTagsUrl.apply, CanonicalTagsUrl.unapply)("url")
  implicit val CanonicalTagsTypeCodecJson: CodecJson[CanonicalTagsType] = CodecJson(
    {
      case x: CanonicalTagsPage => ("tpe" := "page") ->: x.asJson
      case x: CanonicalTagsUrl => ("tpe" := "url") ->: x.asJson
    },
    c => for {
      tpe <- c.downField("tpe").as[String]
      x <- tpe match {
        case "page" => c.as[CanonicalTagsPage]
        case "url" => c.as[CanonicalTagsUrl]
        case _ => DecodeResult.fail(s"Bad tpe=${tpe}", c.history)
      }
    } yield x
  )
  implicit val OtherCanonicalCodecJson: CodecJson[OtherCanonical] = casecodec2(OtherCanonical.apply, OtherCanonical.unapply)("locale", "url")
  implicit val CanonicalTagsCodecJson: CodecJson[CanonicalTags] = casecodec4(CanonicalTags.apply, CanonicalTags.unapply)("tpe", "others", "addLang", "tagAsNew")
  implicit val PageBackgroundCodecJson: CodecJson[PageBackground] = casecodec3(PageBackground.apply, PageBackground.unapply)("img", "color", "fixed")
  implicit val PageCodeInsertionCodecJson: CodecJson[PageCodeInsertion] = casecodec3(PageCodeInsertion.apply, PageCodeInsertion.unapply)("rootClassNames", "rendering", "rendered")
  implicit val CustomThemeCodecJson: CodecJson[CustomTheme] = casecodec2(CustomTheme.apply, CustomTheme.unapply)("colorScheme", "css")
  implicit val PageThemeCodecJson: CodecJson[PageTheme] = CodecJson(
    {
      case PagesetTheme => ("tpe" := "pageset") ->: jEmptyObject
      case x: CustomTheme => ("tpe" := "custom") ->: x.asJson
    },
    c => for {
      tpe <- c.downField("tpe").as[String]
      x <- tpe match {
        case "pageset" => DecodeResult.ok(PagesetTheme)
        case "custom" => c.as[CustomTheme]
        case _ => DecodeResult.fail(s"Bad tpe=${tpe}", c.history)
      }
    } yield x
  )
  implicit val UrlTypeCodecJson: CodecJson[UrlType] = casecodec1(UrlType.apply, UrlType.unapply)("url")
  implicit val PageLinkTypeCodecJson: CodecJson[PageLinkType] = casecodec1(PageLinkType.apply, PageLinkType.unapply)("pageId")
  implicit val PageTypeCodecJson: CodecJson[PageType] = CodecJson(
    {
      case WidgetsType => ("tpe" := "widgets") ->: jEmptyObject
      case x: UrlType => ("tpe" := "url") ->: x.asJson
      case x: PageLinkType => ("tpe" := "page") ->: x.asJson
    },
    c => for {
      tpe <- c.downField("tpe").as[String]
      x <- tpe match {
        case "widgets" => DecodeResult.ok(WidgetsType)
        case "url" => c.as[UrlType]
        case "page" => c.as[PageLinkType]
        case _ => DecodeResult.fail(s"Bad tpe=${tpe}", c.history)
      }
    } yield x
  )
  implicit val ColumnWidgetsJson: CodecJson[ColumnWidgets] = casecodec1(ColumnWidgets.apply, ColumnWidgets.unapply)("widgets")
  implicit val PageCodecJson: CodecJson[Page] = casecodec17(Page.apply, Page.unapply)("parentId", "priority", "name", "title", "url", "orgName", "tpe", "seo", "theme", "background", "layoutScheme", "codeInsertion", "canonicalTags", "menu", "appearance", "permissions", "columns")
  
}
