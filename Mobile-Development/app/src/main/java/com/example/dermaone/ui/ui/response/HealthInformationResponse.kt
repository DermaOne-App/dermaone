package com.example.dermaone.ui.ui.response

import com.google.gson.annotations.SerializedName

data class HealthInformationResponse(

	@field:SerializedName("pagination")
	val pagination: Pagination,

	@field:SerializedName("top_stories_serpapi_link")
	val topStoriesSerpapiLink: String,

	@field:SerializedName("related_questions")
	val relatedQuestions: List<RelatedQuestionsItem>,

	@field:SerializedName("organic_results")
	val organicResults: List<OrganicResultsItem>,

	@field:SerializedName("inline_images")
	val inlineImages: List<InlineImagesItem>,

	@field:SerializedName("related_searches")
	val relatedSearches: List<RelatedSearchesItem>,

	@field:SerializedName("search_metadata")
	val searchMetadata: SearchMetadata,

	@field:SerializedName("filters")
	val filters: List<FiltersItem>,

	@field:SerializedName("search_information")
	val searchInformation: SearchInformation,

	@field:SerializedName("search_parameters")
	val searchParameters: SearchParameters,

	@field:SerializedName("serpapi_pagination")
	val serpapiPagination: SerpapiPagination,

	@field:SerializedName("top_stories_link")
	val topStoriesLink: String
)

data class FiltersItem(

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("parameters")
	val parameters: Parameters,

	@field:SerializedName("serpapi_link")
	val serpapiLink: String
)

data class Parameters(

	@field:SerializedName("q")
	val q: String,

	@field:SerializedName("uds")
	val uds: String
)

data class OtherPages(

	@field:SerializedName("2")
	val jsonMember2: String,

	@field:SerializedName("3")
	val jsonMember3: String,

	@field:SerializedName("4")
	val jsonMember4: String,

	@field:SerializedName("5")
	val jsonMember5: String,

	@field:SerializedName("6")
	val jsonMember6: String,

	@field:SerializedName("7")
	val jsonMember7: String,

	@field:SerializedName("8")
	val jsonMember8: String,

	@field:SerializedName("9")
	val jsonMember9: String,

	@field:SerializedName("10")
	val jsonMember10: String
)

data class SearchMetadata(

	@field:SerializedName("raw_html_file")
	val rawHtmlFile: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("processed_at")
	val processedAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("total_time_taken")
	val totalTimeTaken: Any,

	@field:SerializedName("google_url")
	val googleUrl: String,

	@field:SerializedName("json_endpoint")
	val jsonEndpoint: String,

	@field:SerializedName("status")
	val status: String
)

data class InlineImagesItem(

	@field:SerializedName("thumbnail")
	val thumbnail: String,

	@field:SerializedName("original")
	val original: String,

	@field:SerializedName("source")
	val source: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("source_name")
	val sourceName: String
)

data class RelatedQuestionsItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("snippet")
	val snippet: Any,

	@field:SerializedName("displayed_link")
	val displayedLink: String,

	@field:SerializedName("next_page_token")
	val nextPageToken: String,

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("serpapi_link")
	val serpapiLink: String,

	@field:SerializedName("source_logo")
	val sourceLogo: String,

	@field:SerializedName("list")
	val list: List<String>
)

data class SerpapiPagination(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("current")
	val current: Int,

	@field:SerializedName("other_pages")
	val otherPages: OtherPages,

	@field:SerializedName("next_link")
	val nextLink: String
)

data class Pagination(

	@field:SerializedName("next")
	val next: String,

	@field:SerializedName("current")
	val current: Int,

	@field:SerializedName("other_pages")
	val otherPages: OtherPages
)

data class OrganicResultsItem(

	@field:SerializedName("redirect_link")
	val redirectLink: String,

	@field:SerializedName("displayed_link")
	val displayedLink: String,

	@field:SerializedName("favicon")
	val favicon: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("position")
	val position: Int,

	@field:SerializedName("source")
	val source: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("snippet")
	val snippet: String,

	@field:SerializedName("snippet_highlighted_words")
	val snippetHighlightedWords: List<String>,

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("thumbnail")
	val thumbnail: String,

	@field:SerializedName("sitelinks")
	val sitelinks: Sitelinks
)

data class RelatedSearchesItem(

	@field:SerializedName("block_position")
	val blockPosition: Int,

	@field:SerializedName("query")
	val query: String,

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("serpapi_link")
	val serpapiLink: String
)

data class Sitelinks(

	@field:SerializedName("inline")
	val inline: List<InlineItem>
)

data class SearchParameters(

	@field:SerializedName("q")
	val q: String,

	@field:SerializedName("engine")
	val engine: String,

	@field:SerializedName("gl")
	val gl: String,

	@field:SerializedName("google_domain")
	val googleDomain: String,

	@field:SerializedName("start")
	val start: Int,

	@field:SerializedName("device")
	val device: String
)

data class InlineItem(

	@field:SerializedName("link")
	val link: String,

	@field:SerializedName("title")
	val title: String
)

data class SearchInformation(

	@field:SerializedName("query_displayed")
	val queryDisplayed: String,

	@field:SerializedName("time_taken_displayed")
	val timeTakenDisplayed: Any,

	@field:SerializedName("organic_results_state")
	val organicResultsState: String,

	@field:SerializedName("total_results")
	val totalResults: Int
)
