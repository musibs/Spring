package io.codefountain.spring.mvc.domain;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Feature")
public class Feature {
	
	
	private String featureId;
	private String featureName;
	private String featureDescription;
	private String releaseVersion;
	private String[] featureDevelopers; 

}
