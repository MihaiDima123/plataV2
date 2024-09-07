package com.plata.Plata.guild.repository;

import com.plata.Plata.guild.entity.Guild;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildRepository extends CrudRepository<Guild, Integer> {
    @Query("""
        select count(*)
        from Guild
        where name = :name
    """)
    int getCountByName(String name);
}
