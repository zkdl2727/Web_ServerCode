package com.demo;


import com.spring.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartoonServiceImpl implements CartoonService {

    private ArrayList<CartoonDto> db = new ArrayList<>();

    public CartoonServiceImpl() {
        System.out.println("CartoonServiceImpl 객체 생성");
        db.add(new CartoonDto(1,"신의 탑","webtoon1.jpg","자신의 모든 것이었던 소녀를 쫓아 탑에 들어온 소년 그리고 그런 소년을 시험하는 탑"));
        db.add(new CartoonDto(2,"김부장","webtoon2.jpg","“제발 안경 쓴 아저씨는 건들지 말자…” 오직 자신의 딸 '민지'를 위해 특수요원직을 관두고 평범함을 선택한 가장 김부장."));
        db.add(new CartoonDto(3,"전지적 독자 시점","webtoon3.jpg", "'이건 내가 아는 그 전개다' 한순간에 세계가 멸망하고, 새로운 세상이 펼쳐졌다."));
        db.add(new CartoonDto(4,"외모지상주의","webtoon4.jpg", "못생기고 뚱뚱하다고 괴롭힘을 당하며 루저 인생만 살아온 내가 잘생겨졌다는 이유로 인싸가 됐다. "));
    }

    @Override // 오버라이딩 명시, 오버라이딩 의도 파악.
    public ArrayList<CartoonDto>  select() {
        return db;
    };


    @Override
    public boolean insert(CartoonDto cartoon) {
        db.add(cartoon);
        return true;
    }

    @Override // Null 체크 - read,delete,update 메서드에서 findAny()
    // .get을 사용하기 전에 해당 인덱스에 해당하는 만화가 있는지 확인하고 null을 처리하는 것이 좋습니다.
    // optional을 사용해 orElse(null)을 통해 null값을 처리할 수 있습니다.
    public CartoonDto read(int idx) {
        return db.stream()
                .filter(m -> m.getIdx() == idx)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean delete(int idx) {
        return db.removeIf(m -> m.getIdx() == idx);

    }

    @Override
    public boolean update(CartoonDto cartoon) {
        Optional<CartoonDto> optionalCartoon = db.stream()
                .filter(m -> m.getIdx() == cartoon.getIdx())
                .findFirst();
        if (optionalCartoon.isPresent()) {
            CartoonDto temp = optionalCartoon.get();
            temp.setTitle(cartoon.getTitle());
            temp.setImage(cartoon.getImage());
            temp.setContent(cartoon.getContent());
            return true;
        }
        return false;
    }
    @Override
    public int count() {
        return db.size();
    }

    @Override
    public CartoonDto search(String title) {
        for (CartoonDto cartoon : db) {
            if (cartoon.getTitle().equals(title)) {
                return cartoon;
            }
        }
        return null;
    }
    //        for(MovieDto movie : db) {
//            if(movie.getTitle().startsWith(title.substring(0, 2))) {
//                result.add(movie);
//            }
//            if(movie.getTitle().equals(idx))
//                result.add(movie);
//        }
//
//        MovieDto tn = db.stream().filter(m -> m.getIdx() == idx).findAny().get();
//        return tn; // search(title) 의 값에 맞는 db 데이터를 찾아서 db 데이터를 전부 return
//
}
