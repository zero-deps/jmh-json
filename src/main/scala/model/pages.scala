package model
package pages

final case class Page(
  parentId: String,
  priority: Double,

  name: String Map String,
  title: String Map String,
  url: String Map String,
  orgName: String,
  tpe: PageType,
  seo: PageSeo,
  theme: PageTheme,
  background: PageBackground,
  layoutScheme: String,
  codeInsertion: PageCodeInsertion,
  canonicalTags: CanonicalTags,
  menu: PageMenuConfig,
  appearance: PageAppearance,
  permissions: Permissions,

  columns: List[ColumnWidgets]
)

final case class ColumnWidgets(widgets: List[Widget])

final case class PageSeo(
  description: String Map String,
  keywords: String Map String,
  robots: String Map String,
  sitemap: PageSitemap,
  adobePageTitle: String,
)

final case class PageSitemap(include: Boolean, priority: Option[Double], frequency: String)

final case class Permissions(player: Boolean, guest: Boolean)

final case class PageCodeInsertion(rootClassNames: String, rendering: String, rendered: String)

final case class PageMenuConfig(cssClasses: String, icon: String, target: String, mobile: MenuMobile, tablet: MenuTablet, desktop: MenuDesktop)
final case class MenuMobile(hidden: Boolean, expandable: Boolean, expanded: Boolean, quickDeposit: Boolean)
final case class MenuTablet(hidden: Boolean, expandable: Boolean, expanded: Boolean, quickDeposit: Boolean, navigationBar: Boolean)
final case class MenuDesktop(hidden: Boolean, hoverMenu: Boolean, hoverItems: List[HoverItem], quickDeposit: Boolean, navigationBar: Boolean)

final case class PageAppearance(mobile: Appearance, tablet: Appearance, desktop: Appearance)
final case class Appearance(hideHeader: Boolean, showAsPopup: Boolean, popupWidth: Int, overrideFooter: Boolean, footerWcId: String)

final case class HoverItem(title: String, wcId: Option[String], enabled: Boolean)

final case class CanonicalTags(tpe: CanonicalTagsType, others: List[OtherCanonical], addLang: Boolean, tagAsNew: Boolean)

sealed trait CanonicalTagsType
final case class CanonicalTagsPage(pageId: String) extends CanonicalTagsType
final case class CanonicalTagsUrl(url: String) extends CanonicalTagsType

final case class OtherCanonical(locale: String, url: String)

final case class PageBackground(img: String, color: String, fixed: Boolean)

sealed trait PageTheme
final case object PagesetTheme extends PageTheme
final case class CustomTheme(colorScheme: String, css: String) extends PageTheme

sealed trait PageType
final case object WidgetsType extends PageType
final case class UrlType(url: String) extends PageType
final case class PageLinkType(pageId: String) extends PageType

final case class Widget(id: String, version: Int, config: WidgetConfig)
final case class WidgetConfig(look: WidgetLook, permissions: WidgetPermissions, pref: WidgetPref)

final case class WidgetLook(
  titles: String Map String,
  useCustomTitle: Boolean,
  enabledDevices: Map[String,Boolean],
  mobilePortletOrder: String,
  showBorders: Boolean,
  showMinIcon: Boolean,
  initialWindowState: String,
  customCSSClassName: String,
  customCSS: String,
)

final case class WidgetPermissions(player: Boolean, guest: Boolean)

final case class Layout(id: String, name: String, img: String)

final case class WidgetPref(pref: String)

