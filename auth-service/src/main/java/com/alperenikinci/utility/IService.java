package com.alperenikinci.utility;

import java.util.List;
import java.util.Optional;

/**
 * Servis sözleşmesi, burada kalıp olarak oluşturulan methodlar
 * tüm servislerde aynı şekilde kullanılmak zorundadır.
 * SORUNLAR;
 * 1- Eğer buradaki methodlar tüm servisler tarafından kullanılacak ise,
 * kaydetme işleminde hangi entity adı yazılmalı???
 * Çözüm: Type olarak belirsiz durumlarda, Object gibi katı türleri kullanmak
 * yerine esnek bir şablon çıkartmak doğru olacaktır. Bunu yapabilmek için, GenericType ile çözülür
 * 2- Peki, id her zaman long olacamz, String olabilir, Integer olabilir o zaman id için Long
 * kullanmak sorun yaratacaktır.
 */
public interface IService<T, ID> {
    T save(T t);
    Iterable<T> saveAll(Iterable<T> t);
    T update(T t);
    void delete(T t);
    void deleteById(ID id);
    List<T> findAll();
    Optional<T> findById(ID id);

}
