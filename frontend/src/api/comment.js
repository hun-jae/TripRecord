import { apiInstance } from "./index.js";
const api = apiInstance();

function listComment(param, success, fail) {
  api.get(`/${param.type}comment/selectbybid?bid=${param.bid}`).then(success).catch(fail);
}
function writeComment(param, success, fail) {
  api.post(`/${param.type}comment/write`, JSON.stringify(param.comment)).then(success).catch(fail);
}
function deleteComment(param, success, fail) {
  api
    .get(`/${param.type}comment/delete?cid=${param.cid}`, JSON.stringify(param.comment))
    .then(success)
    .catch(fail);
}
//param에는 type(hotplace, tripway)와 bid가 들어가야함
export { listComment, writeComment, deleteComment };
