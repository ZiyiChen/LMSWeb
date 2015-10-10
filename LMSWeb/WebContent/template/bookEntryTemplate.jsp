<script id="bookEntryTemplate" type="text/x-handlebars-template">
  <tr>
    <td>{{title}}</td>
	<td>{{publisher.publisherName}}</td>
	<td>{{#if authors}}
		{{#each authors}}
			{{authorName}}&nbsp,
		{{/each}}
	{{/if}}</td>
	<td>{{#if genres}}
		{{#each genres}}
			{{genreName}}&nbsp,
		{{/each}}
	{{/if}}</td>
	<td><button type="button" class="btn btn-primary" onclick="javascript:editModal('{{bookId}}','{{authorName}}');">Edit</button></td>
	<td><button type="button" class="btn btn-danger" onclick="javascript:deleteBook('{{bookId}}');">Delete</button></td>
  </tr>
</script>