import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Session session = CreateSessionFactory.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();

        Post post1 = new Post("First");

        Comment comment1 = new Comment("Second");
        comment1.setPost(post1);
        Comment comment2 = new Comment("Third");
        comment2.setPost(post1);
        Comment comment3 = new Comment("Fourth");
        comment3.setPost(post1);

        post1.setComments(new HashSet<>(Arrays.asList(comment1, comment2, comment3)));

        session.persist(post1);

        transaction.commit();

        Post postDB = (Post) session.get(Post.class, 1);
        System.out.println(postDB + " --> " + postDB.getComments());

        Comment commentDB = (Comment) session.get(Comment.class, 2);
        System.out.println(commentDB + " --> " + commentDB.getPost());

        session.close();

    }
}
