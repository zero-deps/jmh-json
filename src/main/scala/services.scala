package services

import argonaut.Argonaut._
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

object States {

  @State(Scope.Benchmark)
  class CommonState {
    val page = {
      import model.pages.{Page, WidgetsType, PageSeo, PageSitemap, PagesetTheme, PageBackground, PageCodeInsertion, CanonicalTags, CanonicalTagsPage, OtherCanonical, PageMenuConfig, MenuMobile, MenuTablet, MenuDesktop, HoverItem, PageAppearance, Appearance, Permissions}
      Page( parentId="1"
          , priority=1.23
          , name=Map("en_US" -> "test_page_1", "de_DE" -> "testseite_1")
          , title=Map("en_US" -> "test_page_1_title", "de_DE" -> "testseite_1_titel")
          , url=Map("en_US" -> "/testpage1")
          , orgName="orgName"
          , tpe=WidgetsType
          , seo=PageSeo(description=Map.empty, keywords=Map.empty, robots=Map.empty, sitemap=PageSitemap(include=true, priority=Some(1.23), frequency="456"), adobePageTitle="adobePageTitle")
          , theme=PagesetTheme
          , background=PageBackground(img="img1", color="red", fixed=false)
          , layoutScheme="column-1"
          , codeInsertion=PageCodeInsertion(rootClassNames="rootClassNames", rendering="rendering", rendered="rendered")
          , canonicalTags=CanonicalTags(tpe=CanonicalTagsPage("456"), others=List(OtherCanonical(locale="de_DE", url="/testseite1")), addLang=false, tagAsNew=true)
          , menu=PageMenuConfig( cssClasses="cssClasses"
                               , icon="icon"
                               , target="target"
                               , mobile=MenuMobile(hidden=false, expandable=false, expanded=false, quickDeposit=false)
                               , tablet=MenuTablet(hidden=false, expandable=true, expanded=true, quickDeposit=true, navigationBar=false)
                               , desktop=MenuDesktop(hidden=false, hoverMenu=true, hoverItems=List(HoverItem(title="hover", wcId=Some("hover wc"), enabled=true)), quickDeposit=true, navigationBar=true)
                               )
          , appearance=PageAppearance( mobile=Appearance(hideHeader=false, showAsPopup=false, popupWidth=0, overrideFooter=true, footerWcId="mobile_footer")
                                     , tablet=Appearance(hideHeader=false, showAsPopup=false, popupWidth=0, overrideFooter=true, footerWcId="tablet_footer")
                                     , desktop=Appearance(hideHeader=false, showAsPopup=false, popupWidth=0, overrideFooter=true, footerWcId="desktop_footer")
                                     )
          , permissions=Permissions(player=true, guest=true)
          , columns=List.empty
          )
    }
    val protoPage = {
      import model.proto.pages.{Page, WidgetsType, PageSeo, PageSitemap, PagesetTheme, PageBackground, PageCodeInsertion, CanonicalTags, CanonicalTagsPage, OtherCanonical, PageMenuConfig, MenuMobile, MenuTablet, MenuDesktop, HoverItem, PageAppearance, Appearance, Permissions}
      Page( parentId="1"
          , priority=1.23
          , name=Map("en_US" -> "test_page_1", "de_DE" -> "testseite_1")
          , title=Map("en_US" -> "test_page_1_title", "de_DE" -> "testseite_1_titel")
          , url=Map("en_US" -> "/testpage1")
          , orgName="orgName"
          , tpe=WidgetsType()
          , seo=PageSeo(description=Map.empty, keywords=Map.empty, robots=Map.empty, sitemap=PageSitemap(include=true, priority=Some(1.23), frequency="456"), adobePageTitle="adobePageTitle")
          , theme=PagesetTheme()
          , background=PageBackground(img="img1", color="red", fixed=false)
          , layoutScheme="column-1"
          , codeInsertion=PageCodeInsertion(rootClassNames="rootClassNames", rendering="rendering", rendered="rendered")
          , canonicalTags=CanonicalTags(tpe=CanonicalTagsPage("456"), others=List(OtherCanonical(locale="de_DE", url="/testseite1")), addLang=false, tagAsNew=true)
          , menu=PageMenuConfig( cssClasses="cssClasses"
                               , icon="icon"
                               , target="target"
                               , mobile=MenuMobile(hidden=false, expandable=false, expanded=false, quickDeposit=false)
                               , tablet=MenuTablet(hidden=false, expandable=true, expanded=true, quickDeposit=true, navigationBar=false)
                               , desktop=MenuDesktop(hidden=false, hoverMenu=true, hoverItems=List(HoverItem(title="hover", wcId=Some("hover wc"), enabled=true)), quickDeposit=true, navigationBar=true)
                               )
          , appearance=PageAppearance( mobile=Appearance(hideHeader=false, showAsPopup=false, popupWidth=0, overrideFooter=true, footerWcId="mobile_footer")
                                     , tablet=Appearance(hideHeader=false, showAsPopup=false, popupWidth=0, overrideFooter=true, footerWcId="tablet_footer")
                                     , desktop=Appearance(hideHeader=false, showAsPopup=false, popupWidth=0, overrideFooter=true, footerWcId="desktop_footer")
                                     )
          , permissions=Permissions(player=true, guest=true)
          , columns=List.empty
          )
    }
    val argonautCodec = model.pagesCodec.PageCodecJson

    def enByte(p: model.proto.pages.Page): model.proto.pages.EnByte = {
      import model.proto.pages.{EnByte, Author}
      import com.google.protobuf.ByteString
      EnByte( fid = "pages_123_2"
            , id = "1"
            , prev = "0"
            , author = Author(username="username", firstName="firstName", lastName="lastName")
            , modified = 123456789
            , version = "123"
            , comment = "pages_comment"
            , data = ByteString.copyFrom(p.toByteArray)
            )
    }

    def enByteWithPage: model.proto.pages.EnByte = {
      import model.proto.pages.{EnByte, Author}
      import com.google.protobuf.ByteString
      EnByte( fid = "pages_123_2"
            , id = "1"
            , prev = "0"
            , author = Author(username="username", firstName="firstName", lastName="lastName")
            , modified = 123456789
            , version = "123"
            , comment = "pages_comment"
            , data = ByteString.copyFrom(protoPage.toByteArray)
            )
    }

    val encodedEnByte: Array[Byte] = enByteWithPage.toByteArray

    def en = model.proto.pages.En( fid = "pages_123_2"
                                 , id = "1"
                                 , prev = "0"
                                 , author = model.proto.pages.Author(username="username", firstName="firstName", lastName="lastName")
                                 , modified = 123456789
                                 , version = "123"
                                 , comment = "pages_comment"
                                 )

    val enPage: model.proto.pages.EnPage = {
      import model.proto.pages.{En, EnPage, Author}
      EnPage( en = en
            , page = protoPage
            )
    }

    val encodedEnPage: Array[Byte] = enPage.toByteArray
  }
}

class ProtoVsArgonaut {
  import States._

  @Benchmark
  def proto2Bench(state: CommonState): Unit = {
    model.proto.pages.Page.parseFrom(state.protoPage.toByteArray)
  }

  @Benchmark
  def argonautBench(state: CommonState): Unit = {
    val byteArray: Array[Byte] = state.page.asJson(state.argonautCodec).nospaces.getBytes("UTF-8")
    val str = new String(byteArray, "UTF-8")
    str.decodeEither[model.pages.Page](state.argonautCodec).toOption.get
  }
}

class ProtoEncode {
    import States._

    @Benchmark
    def protoEnPage(state: CommonState): Unit = {
      state.enPage.toByteArray
    }

    @Benchmark
    def protoEnByteWithPage(state: CommonState): Unit = {
      state.enByteWithPage.toByteArray
    }

    @Benchmark
    def protoEnByteOnly(state: CommonState): Unit = {
      state.enByte(state.protoPage).toByteArray
    }
  }

  class ProtoDecode {
    import States._

    @Benchmark
    def protoEnPage(state: CommonState): Unit = {
      model.proto.pages.EnPage.parseFrom(state.encodedEnPage)
    }

    @Benchmark
    def protoEnByteWithPage(state: CommonState): Unit = {
      val enbyte = model.proto.pages.EnByte.parseFrom(state.encodedEnByte)
      model.proto.pages.Page.parseFrom(enbyte.data.newCodedInput())
    }

    @Benchmark
    def protoEnByteOnly(state: CommonState): Unit = {
      model.proto.pages.EnByte.parseFrom(state.encodedEnByte)
    }
  }
