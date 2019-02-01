package pl.codecity.perun.common.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "article")
@DynamicInsert
@DynamicUpdate
@SuppressWarnings("serial")
public class Article extends Post implements Comparable<Article> {

	@Override
	public int compareTo(Article article) {
		if (getDate() != null && article.getDate() == null) return 1;
		if (getDate() == null && article.getDate() != null) return -1;
		if (getDate() != null && article.getDate() != null) {
			int r = getDate().compareTo(article.getDate());
			if (r != 0) return r * -1;
		}
		return (int) (article.getId() - getId());
	}
}
