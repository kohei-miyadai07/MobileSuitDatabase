--モビルスーツ
insert 
into MobileSuit 
values ( 
  'as000178'
  , 'RX-78-02'
  , 'ガンダム'
  , '/images/RX-78-02.PNG'
  , 18
  , 72.5
  , 73.25
  , 'ミノフスキー・イヨネスコ型熱核反応炉'
  , 'ルナ・チタニウム合金'
  , 27000
  , 350000
  , 'テスト'
  , 'テスト'
  , '2022-02-13 11:41:23'
  , '2022-02-13 11:41:23'
);


--武装
insert 
into Armed 
values ('00000001', 'テスト用ビームライフル', 'テスト用です。');

insert 
into Armed 
values ('00000002', 'テスト用ビームサーベル', 'テストサーベル用です。');

insert 
into Armed 
values ('00000003', 'テスト用シールド', 'テスト用シールドです。');

insert 
into Armed 
values ('00000004', 'テスト用バズーカ', 'テスト用バズーカです。');

--装備
insert 
into Equipment 
values ( 
  'o9200uy7'
  , 'as000178'
  , '00000001'
  , 1
);

insert 
into Equipment 
values ( 
  'AS200uy7'
  , 'as000178'
  , '00000002'
  , 2
);

insert 
into Equipment 
values ( 
  'o920WEy7'
  , 'as000178'
  , '00000003'
  , 1
);
