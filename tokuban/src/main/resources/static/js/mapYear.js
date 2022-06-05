//jQuery用の記述ここから
var head = document.getElementsByTagName('head')
var script = document.createElement('script')
script.setAttribute('src', 'https://code.jquery.com/jquery-1.12.4.min.js')
script.setAttribute('type', 'text/javascript')
//jQuery用の記述ここまで

//この関数の中にjQueryの記述をする
script.addEventListener('load', function() {

	$(function () {
        wh_change();
      });
      $(window).resize(function () {
        wh_change();
    
      });
      function wh_change() {
        sW = window.innerWidth;
        sH = window.innerHeight;
        if (sW > sH) {						// 横長
          if (sH < 400) {					// 縦が400pxより小さい
            $('#clickable_map br').css('display', 'none');
            $('#clickable_map a').css('line-height', '17px');
            $('#clickable_map a').css('padding', '1px 0');
            $('#hokkaido').css('height', '26px');
            $('#hokkaido').css('line-height', '17px');
            $('.char3 a').css('line-height', '17px');
            $('.char3 a').css('font-size', '14px');
            $('.rowspan_2 a').css('line-height', '36px');
            $('#clickable_map td').not('#dummy').css('height', '20px');		// .not(セレクタ) 除外
          } else if (sH < 600) {					// 縦が600pxより小さい
            $('#clickable_map br').css('display', 'none');
            $('#clickable_map a').css('line-height', '30px');
            $('#clickable_map a').css('padding', '1px 0');
            $('#hokkaido').css('height', '49px');
            $('#hokkaido').css('line-height', '30px');
            $('.char3 a').css('line-height', '30px');
            $('.char3 a').css('font-size', '14px');
            $('.rowspan_2 a').css('line-height', '60px');
            $('#clickable_map td').not('#dummy').css('height', '24px');
          } else {
            $('#clickable_map br').css('display', 'none');
            $('#clickable_map a').css('line-height', '49px');
            $('#clickable_map a').css('padding', '1px 0');
            $('#hokkaido').css('height', '49px');
            $('#hokkaido').css('line-height', '49px');
            $('.char3 a').css('line-height', '49px');
            $('.char3 a').css('font-size', '14px');
            $('.rowspan_2 a').css('line-height', '99px');
            $('#clickable_map td').not('#dummy').css('height', '24px');
          }
        } else {						// 縦長 (スマホ)
          $('#clickable_map br').css('display', '');
          $('#clickable_map a').css('line-height', '14px');
          $('#clickable_map a').css('padding', '5px 0');
          $('#hokkaido').css('height', '49px');
          $('#hokkaido').css('line-height', '14px');
          $('.char3 a').css('line-height', '12px');
          $('.char3 a').css('font-size', '12px');
          $('.rowspan_2 a').css('line-height', '30px');
          $('#clickable_map td').not('#dummy').css('height', '24px');
        }
      }

})
document.head.appendChild(script)