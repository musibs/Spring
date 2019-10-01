package io.codefountain.spring.infoq.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString	
public class GithubProject implements Serializable {

	private static final long serialVersionUID = -901581368015267774L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String orgName;
	
	@Column(unique = true)
	private String repoName;
}
