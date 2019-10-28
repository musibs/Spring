package io.codefountain.spring.mvc.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@XmlRootElement(name = "features")
@NoArgsConstructor
@AllArgsConstructor
@Setter
//@Getter
public class FeatureList {

	
	private List<Feature> listOfFeatures;
	
	@XmlElement(name = "feature")
	public List<Feature> getListOfFeatures() {
		return listOfFeatures;
	}
}
