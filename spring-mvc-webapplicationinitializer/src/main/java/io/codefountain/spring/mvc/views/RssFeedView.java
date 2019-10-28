package io.codefountain.spring.mvc.views;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.feed.AbstractRssFeedView;

import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Content;
import com.rometools.rome.feed.rss.Item;

import io.codefountain.spring.mvc.domain.Feature;
import io.codefountain.spring.mvc.domain.FeatureList;

public class RssFeedView extends AbstractRssFeedView{
	
	@Override
	protected void buildFeedMetadata(Map<String, Object> model, Channel feed, HttpServletRequest request) {

		feed.setTitle("New Feature List");
		feed.setDescription("List of new feature added in this release");
		feed.setLink("https://medium.com/musibs");
		
		super.buildFeedMetadata(model, feed, request);
	}

	@Override
	protected List<Item> buildFeedItems(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		FeatureList features = (FeatureList) model.get("features");
		List<Item> items = new ArrayList<>(features.getListOfFeatures().size());
		
		features.getListOfFeatures().forEach(feature -> {
			String message = feature.toString();
			
			Content content = new Content();
			content.setValue(message);
			
			Item item = new Item();
			item.setAuthor("Code Fountain");
			item.setLink("https://medium.com/musibs");
			item.setContent(content);
			items.add(item);
		});
		
		return items;
	}

}
