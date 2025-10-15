package udemy.courses.libraryapi.repository.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import udemy.courses.libraryapi.model.Book;
import udemy.courses.libraryapi.model.GenderBook;

public class BookSpecification {

    public static Specification<Book> isbnEqual(String isbn) {
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Book> titleLike(String title) {
        return (root, query, cb) ->
                cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> genderEqual(GenderBook gender) {
        return (root, query, cb) -> cb.equal(root.get("gender"), gender);
    }

    public static Specification<Book> publishYearEqual(Integer publishYear) {
        return (root, query, cb) ->
                cb.equal(
                        cb.function("to_char", String.class, root.get("publishDate"), cb.literal("yyyy")),
                        publishYear.toString()
                );
    }

    public static Specification<Book> authorNameLike(String name) {
        return (root, query, cb) -> {

            Join<Object, Object> joinAuthor = root.join("author", JoinType.INNER);
            return cb.like(cb.lower(joinAuthor.get("name")), "%" + name.toLowerCase() + "%");

            // Versão resuimida, porem sem controle do tipo do join
            // return cb.like(cb.lower(root.get("author").get("name")), "%" + name.toLowerCase() + "%");
        };
    }
}
