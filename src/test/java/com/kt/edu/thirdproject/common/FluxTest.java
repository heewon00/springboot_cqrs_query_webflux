package com.kt.edu.thirdproject.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class FluxTest {
    @Test
    public void flux_just_consumer() {
        List<String> names = new ArrayList<String>();
        Flux<String> flux = Flux.just("자바", "스칼라", "파이썬").log();
        flux.subscribe(s -> {
            System.out.println("시퀀스 수신 : " + s);
            names.add(s);
        });
        assertEquals(names, Arrays.asList("자바", "스칼라", "파이썬"));
    }

    //------------------------------------------------------------

    @Test
    public void ArrayTest() {
        List<String> names = new ArrayList<String>(Arrays.asList("자바", "스칼라", "파이썬"));
        for (String s : names) {
            System.out.println("시퀀스 수신 : " + s);
        }
        Iterator iter = names.iterator();
        while (iter.hasNext()) {
            System.out.println("Iter 시퀀스 수신: " + iter.next());
        }
    }

    @Test
    //list받은걸 stream으로 바꿔서 람다식으로 하나씩 출력
    public void ArrayStreamTest() {
        List<String> names = new ArrayList<String>(Arrays.asList("자바", "스칼라", "파이썬"));
        names.stream()
                .filter(s -> s.equals("자바")) // 값이 "자바"인 요소만 필터링
                .forEach(s ->
                        System.out.println("시퀀스 수신 : " + s)
                );
    }

    //filter와 collect 사용하기
    @Test
    public void FilteredListTest() {
        List<String> names = new ArrayList<String>(Arrays.asList("자바", "스칼라", "파이썬"));
        List<String> FilteredList = names.stream()
                .filter(s -> s.equals("자바")) // 값이 "자바"인 요소만 필터링
                .collect(Collectors.toList()); //스트림을 리스트로 반환
        System.out.println("FilteredList :" + FilteredList);

    }

    //------------------------------------------------------------

    @AllArgsConstructor
            // Member 클래스 정의
    class Member {
        @Getter
        private String name;
        @Getter
        private int age;
    }


    // 테스트 메서드
    @Test
    public void ArrayStreamList2() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("자바", 20));
        members.add(new Member("스칼라", 30));
        members.add(new Member("파이썬", 40));

        // 나이만 따로 추출하여 리스트로 만듦
        List<Integer> ages = members.stream()
                .filter(s -> s.getAge() > 30)
                .map(Member::getAge) // Member 객체에서 age 값만 추출
                .collect(Collectors.toList()); // 스트림을 리스트로 반환
        System.out.println("AgeList: " + ages);

        //이름만 따로 추출하여 리스트로 만듦
        List<String> names = members.stream()
                .map(Member::getName) // Member 객체에서 name 값만 추출
                .collect(Collectors.toList()); // 스트림을 리스트로 반환
        System.out.println("NameList: " + names);
    }

    //------------------------------------------------------------

    @Test
    public void SubscriberTest() {
        Flux.range(1, 3) // 1부터 3까지 세 개의 이벤트를 발생시키는 Publisher
        .subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("[Subscriber] onSubscribe");
                subscription.request(3); //request를 주지 않으면 아무 것도 하지 않는다
//                subscription.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("[Subscriber] onNext: " + item);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("[Subscriber] onError: " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("[Subscriber] onComplete");
            }

        });
    }
}
