require 'rest-client'

url = 'http://localhost:8080/comments'

response = RestClient.get url
puts response.code
puts response

json = { title: "chihmin", content: "YAYAYAHHHHHH"}.to_json

RestClient.post url, json, :content_type => :json, :accept => :json

response = RestClient.get url
puts response.code
puts response