package com.demo;


import com.spring.Service;

import java.util.ArrayList;

@Service
public interface CartoonService {

    public ArrayList<CartoonDto> select();

    public CartoonDto read(int idx);

    public int count();

    public boolean delete(int idx);

    public boolean insert(CartoonDto cartoon);

    public boolean update(CartoonDto cartoon);

    public CartoonDto search(String title);

}
