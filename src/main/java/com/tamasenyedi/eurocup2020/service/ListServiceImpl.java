package com.tamasenyedi.eurocup2020.service;

import com.tamasenyedi.eurocup2020.dto.WinnerDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListServiceImpl implements ListService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WinnerDto> retrieveWinnersList() {
        String query = "SELECT u.email, c.code FROM User u JOIN Coupon c ON u.id = c.redeemer.id WHERE c.winner = true ";
        List<Object[]> results = em.createQuery(query).getResultList();
        List<WinnerDto> winners = results
                .stream()
                .map(result -> new WinnerDto(String.valueOf(result[0]), String.valueOf(result[1])))
                .collect(Collectors.toList());
        em.close();
        return winners;
    }
}
