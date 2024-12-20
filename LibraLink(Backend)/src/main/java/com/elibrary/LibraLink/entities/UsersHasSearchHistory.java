package com.elibrary.LibraLink.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users_has_search_history")
public class UsersHasSearchHistory {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="search_history_id")
    private SearchHistory searchHistory;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
