const res1 = /(\d)(\d)\2\1/.exec('1221');
console.log(res1[0], res1[1], res1[2]); // 1221 1 2
console.log(new RegExp("(\\d)(\\d)\\2\\1").test('1221')); // true


const reg = new RegExp("www\\.(.*?)\\.com", 'g');
const str = "https://www.bilibili.com, https://www.baidu.com";
let res;
while (res = reg.exec(str)) {
    console.log(res[1]); // bilibili baidu
}


