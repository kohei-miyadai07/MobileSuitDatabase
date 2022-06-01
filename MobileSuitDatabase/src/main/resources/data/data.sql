--武器
insert 
into Arms 
values ('00000001', 'テスト用ビームライフル', 'テスト用です。');

insert 
into Arms 
values ('00000002', 'テスト用ビームサーベル', 'テストサーベル用です。');

insert 
into Arms 
values ('00000003', 'テスト用シールド', 'テスト用シールドです。');

insert 
into Arms 
values ('00000004', 'テスト用バズーカ', 'テスト用バズーカです。');

--分類
insert 
into Grouping 
values ('b001', '試作モビルスーツ', '試作モビルスーツです。');

--モビルスーツ
insert 
into MobileSuit 
values ( 
  'as000178'
  , 'RX-78-02'
  , 'ガンダム'
  , '/images/RX-78-02.PNG'
  , 'b001'
  , 18
  , 19
  , 72.5
  , 73.25
  , 'ミノフスキー・イヨネスコ型熱核反応炉'
  , 'ルナ・チタニウム合金'
  , 34000
  , 27000
  , 350000
  , 'テスト'
  , 'テスト'
  , '2022-02-13 11:41:23'
  , '2022-02-13 11:41:23'
  , '1'
);

--武装
insert 
into Armed 
values ( 
  '00000001'
  , 'as000178'
  , '装備中1'
  , 1
);

insert 
into Armed 
values ( 
  '00000002'
  , 'as000178'
  , '装備中2'
  , 2
);

insert 
into Armed 
values ( 
  '00000003'
  , 'as000178'
  , '装備中3'
  , 1
);

