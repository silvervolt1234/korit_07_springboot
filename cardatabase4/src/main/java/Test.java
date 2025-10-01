/*
1. 온라인 서점에서 판매할 일반 도서와(Book) 전자책(Ebook)을 관리하는 시스템을 구축하려는데 Book 클래스를 상속받는 Ebook 클래스를 완성하고 각 클래스의 객체를 생성해 정보를 출력하는 프로그램을 작성

요구사항 명세서 :

1. Book 크래스는 title(제목)과 author(저자)를 필드로 가진다
2. Ebook 클래스는 Book 클래스를 상속 받으며 fileSize(파일 크기) 필드를 추가로 보유
3. 각 클래스는 필드 정보를 출력하는 displayInfo() 메서드를 보유. Ebook 클래스의 displayInfo()는 Book의 메서드를 오버라이딩해 파일 크기 정보를 함께 출력
4. main 메서드에서 Book과 Ebook 객체를 각각 생성하고 displayInfo() 메서드를 호출해 실행 예처럼 출력

실행 예
제목 : 자바의 정석, 저자 : 남궁성
제목 : 스프링 부트 3 백과사전, 저자 : 김영한, 파일 크기 : 20.5MB
 */
class Book {
    public String title;
    public String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void displayInfo() {
        System.out.println("제목: " + title + ", 저자: " + author);
    }
}

class Ebook extends Book {
    public double fileSize;

    public Ebook(String title, String author, double fileSize) {
        super(title, author);
        this.fileSize = fileSize;
    }

    @Override
    public void displayInfo() {
        System.out.println("제목: " + title + ", 저자: " + author + ", 파일 크기 : " + fileSize + "MB");
    }
}

public class Test {
    public static void main(String[] args) {
        Book book = new Book("자바의 정석", "남궁성");
        book.displayInfo();
        Ebook ebook = new Ebook("스프링 부트 3 백과사전", "김영한", 20.5);
        ebook.displayInfo();
    }
}
