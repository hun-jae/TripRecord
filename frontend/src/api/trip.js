import { tripInstance } from "./index.js";

const api = tripInstance();

function searchSido(success, fail) {
  api.get(`/searchsido`).then(success).catch(fail);
}

function searchList(param, success, fail) {
  api
    .get(
      `/search?sido_code=${param.sido_code}&keyword=${param.keyword}&content_type_id=${param.content_type_id}`
    )
    .then(success)
    .catch(fail);
}
function getArticleAttractions(bid, success, fail) {
  api.get(`/search/${bid}`).then(success).catch(fail);
}
function getAttraction(contentId, success, fail) {
  api.get(`/searchattr/${contentId}`).then(success).catch(fail);
}
function registAttraction(param, success, fail) {
  api.post(`/regist`, JSON.stringify(param)).then(success).catch(fail);
}
function deleteAttractions(bid, success, fail) {
  api.get(`/deleteattr/${bid}`).then(success).catch(fail);
}

//여긴 수정 전

function getArticle(bid, success, fail) {
  api.get(`/view?bid=${bid}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/tripway`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/tripway/${articleno}`).then(success).catch(fail);
}

export {
  searchSido,
  searchList,
  getArticleAttractions,
  getAttraction,
  registAttraction,
  deleteAttractions,
  getArticle,
  modifyArticle,
  deleteArticle,
};
